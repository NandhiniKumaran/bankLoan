package com.demo.bank.data;


import com.demo.bank.entity.Credit;
import com.demo.bank.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Loan findById(long id);
    List<Loan> findByCredit(Credit credit);

}
