package com.demo.bank.service;

import com.demo.bank.dto.LoanDto;
import com.demo.bank.entity.Loan;

import java.util.List;

public interface LoanService {
    Loan getLoanById(long id);
    LoanDto getLoan(long id);
    LoanDto saveLoanForAccount(LoanDto loanDto);

    Loan saveLoanForAccount(Loan loan);
    List<LoanDto> getLoanByAccountNo(long accountNo);

}
