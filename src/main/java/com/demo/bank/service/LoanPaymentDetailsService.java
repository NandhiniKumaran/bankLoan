package com.demo.bank.service;

import com.demo.bank.data.LoanPaymentDetailsRepository;
import com.demo.bank.entity.Loan;
import com.demo.bank.entity.LoanPaymentDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanPaymentDetailsService {
    private final LoanPaymentDetailsRepository loanPaymentDetailsRepository ;
    private final LoanService loanService;
    public LoanPaymentDetailsService(LoanPaymentDetailsRepository loanPaymentDetailsRepository, LoanService loanService) {
        this.loanPaymentDetailsRepository = loanPaymentDetailsRepository;
        this.loanService = loanService;
    }


    public LoanPaymentDetails saveNewPayment(LoanPaymentDetails loanPaymentDetails)
    {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        return loanPaymentDetails;
    }

    public List<LoanPaymentDetails> findByLoan(Loan loan){
        return loanPaymentDetailsRepository.findByLoanAndIsDeletedIsFalse(loan);
    }



}