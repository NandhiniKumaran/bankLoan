package com.demo.bank.data;


import com.demo.bank.entity.Loan;
import com.demo.bank.entity.LoanPaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanPaymentDetailsRepository extends JpaRepository<LoanPaymentDetails, Long> {

    List<LoanPaymentDetails> findByLoanAndIsDeletedIsFalse(Loan loan);



}
