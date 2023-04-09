package com.demo.bank.service;

import com.demo.bank.data.LoanRepository;
import com.demo.bank.entity.Credit;
import com.demo.bank.entity.Loan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository ;
    private final CreditService creditService ;

    public LoanService(LoanRepository loanRepository, CreditService creditService) {
        this.loanRepository = loanRepository;
        this.creditService = creditService;
    }

    public List<Loan> getAllCredits(){
        return loanRepository.findAll();
    }

    public Loan getLoanById(long id) {return loanRepository.findById(id);}

    public Loan saveLoanForAccount(Loan loan){
        loanRepository.save(loan);
        return loan;
    }

    public List<Loan> getLoanByAccountNo(long accountNo) {
        Credit credit=creditService.retrieveCreditAccount(accountNo);
        return loanRepository.findByCredit(credit);

    }






}