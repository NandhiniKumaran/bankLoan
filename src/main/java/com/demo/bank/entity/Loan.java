package com.demo.bank.entity;

import com.demo.bank.dto.LoanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="LOAN")
public class Loan{

    @Id
    @Column(name="LOAN_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;

    @Column(name="TYPE")
    private LoanType loanType;

    @Column(name="AMOUNT")
    private double amount;

    @Column(name="CURRENCY")
    private String currency;

    @Column(name="START_DATE")
    private LocalDate startDate;

    @Column(name="END_DATE")
    private LocalDate endDate;

    @Column(name="INTEREST_RATE")
    private double interest;

    @Column(name="PAYMENT_STATUS")
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "ACC_NO")
    private Credit credit;

    @Column(name="CREATED_ON")
    private LocalDate createdOn;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="UPDATED_ON")
    private LocalDate updatedOn;

    @Column(name="UPDATED_BY")
    private String updatedBy;

}