package com.demo.bank.service.impl;

import com.demo.bank.data.LoanRepository;
import com.demo.bank.dto.CustomUser;
import com.demo.bank.dto.LoanDto;
import com.demo.bank.entity.Credit;
import com.demo.bank.entity.Loan;
import com.demo.bank.service.CreditService;
import com.demo.bank.service.LoanService;
import com.demo.bank.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoanRepository loanRepository ;
    @Autowired
    CreditService creditService ;

    @Autowired
    UserService userService ;

    @Override
    public Loan getLoanById(long id) {return loanRepository.findById(id);}

    @Override
    public LoanDto getLoan(long id) {
        Loan loan= loanRepository.findById(id);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(loan, LoanDto.class);
    }

    @Override
    public LoanDto saveLoanForAccount(LoanDto loanDto){

        //User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser user=userService.getCurrentUser();
        ModelMapper mapper = new ModelMapper();
        Loan loan=mapper.map(loanDto, Loan.class);
        loan.setCredit(creditService.retrieveCreditAccount(loanDto.getCredit().getId()));
        loan.setCreatedOn(LocalDate.now());
        loan.setCreatedBy(user.getUsername());
        loanRepository.save(loan);
        loanDto.setLoanId(loan.getLoanId());
        return loanDto;
    }

    @Override
    public Loan saveLoanForAccount(Loan loan){
        loanRepository.save(loan);
        return loan;
    }
    @Override
    public List<LoanDto> getLoanByAccountNo(long accountNo) {
        Credit credit=creditService.retrieveCreditAccount(accountNo);
         List<Loan> loanList=loanRepository.findByCredit(credit);
        ModelMapper mapper = new ModelMapper();
        return Arrays.asList(mapper.map(loanList, LoanDto[].class));

    }






}