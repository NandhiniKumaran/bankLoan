package com.demo.bank.service;

import com.demo.bank.dto.LoanPaymentDetailsDto;
import com.demo.bank.entity.Loan;
import com.demo.bank.entity.LoanPaymentDetails;

import java.util.List;

public interface LoanPaymentDetailsService {
    LoanPaymentDetailsDto saveNewPayment(LoanPaymentDetailsDto loanPaymentDetailsDto);
    List<LoanPaymentDetails> findByLoan(Loan loan);
    List<LoanPaymentDetailsDto> findByLoanId(long loanId);


}
