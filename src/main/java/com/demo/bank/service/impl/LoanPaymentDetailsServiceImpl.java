package com.demo.bank.service.impl;

import com.demo.bank.data.LoanPaymentDetailsRepository;
import com.demo.bank.dto.CreditDto;
import com.demo.bank.dto.CustomUser;
import com.demo.bank.dto.LoanPaymentDetailsDto;
import com.demo.bank.entity.Credit;
import com.demo.bank.entity.Loan;
import com.demo.bank.entity.LoanPaymentDetails;
import com.demo.bank.service.LoanPaymentDetailsService;
import com.demo.bank.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class LoanPaymentDetailsServiceImpl implements LoanPaymentDetailsService {
    private final LoanPaymentDetailsRepository loanPaymentDetailsRepository ;
    private final LoanServiceImpl loanService;
    public LoanPaymentDetailsServiceImpl(LoanPaymentDetailsRepository loanPaymentDetailsRepository, LoanServiceImpl loanService) {
        this.loanPaymentDetailsRepository = loanPaymentDetailsRepository;
        this.loanService = loanService;
    }

    @Autowired
    UserService userService;


    @Override
    public LoanPaymentDetailsDto saveNewPayment(LoanPaymentDetailsDto loanPaymentDetailsDto)
    {
        ModelMapper mapper=new ModelMapper();
        LoanPaymentDetails loanPaymentDetails=mapper.map(loanPaymentDetailsDto,LoanPaymentDetails.class);
        loanPaymentDetails.setLoan(loanService.getLoanById(loanPaymentDetails.getLoan().getLoanId()));
        //User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser user=userService.getCurrentUser();
        loanPaymentDetails.setCreatedBy(user.getUsername());
        loanPaymentDetails.setCreatedOn(LocalDate.now());

        Loan loan =loanService.getLoanById(loanPaymentDetails.getLoan().getLoanId());
        List<LoanPaymentDetails> loanPaymentDetailsList=findByLoan(loan);
        Double totalPaidAmount=loanPaymentDetails.getPaidAmount();


        if(loanPaymentDetailsList.size()!=0)
        {
            totalPaidAmount=loanPaymentDetailsList.stream().mapToDouble(paymentDetails-> paymentDetails.getPaidAmount()).sum();
            totalPaidAmount=totalPaidAmount+loanPaymentDetails.getPaidAmount();

            loan.setUpdatedOn(LocalDate.now());
            loan.setUpdatedBy(user.getUsername());

        }
        if((loan.getAmount()<=totalPaidAmount) || (loanPaymentDetails.getRemarks()!=null && loanPaymentDetails.getRemarks().contains("collateral")))
        {
            loan.setPaymentStatus("FULL");
        }else {
            loan.setPaymentStatus("PARTIAL");
        }
        loan=loanService.saveLoanForAccount(loan);
        loanPaymentDetails.setLoan(loan);
        loanPaymentDetailsRepository.saveAndFlush(loanPaymentDetails);
        loanPaymentDetailsDto.setPaymentNo(loanPaymentDetails.getPaymentNo());
        return loanPaymentDetailsDto;
    }

    @Override
    public List<LoanPaymentDetails> findByLoan(Loan loan){
        return loanPaymentDetailsRepository.findByLoan(loan);
    }

    @Override
    public List<LoanPaymentDetailsDto> findByLoanId(long loanId){

        Loan loan= loanService.getLoanById(loanId);
        List<LoanPaymentDetails> loanPaymentDetailsList=loanPaymentDetailsRepository.findByLoan(loan);
        ModelMapper mapper = new ModelMapper();
        return Arrays.asList(mapper.map(loanPaymentDetailsList, LoanPaymentDetailsDto[].class));
    }



}