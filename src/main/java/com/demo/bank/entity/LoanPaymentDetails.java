package com.demo.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="LOAN_PAYMENT_DETAILS")
public class LoanPaymentDetails {

    @Id
    @Column(name="PAYMENT_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentNo;

    @Column(name="PAID_AMOUNT")
    private double paidAmount;

    @Column(name="PAID_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate paidDate;

    @Column(name="REMARKS")
    private String remarks;

    @Column(name="IS_DELETED")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "LOAN_NO")
    private Loan loan;

    @Column(name="CREATED_ON")
    private LocalDate createdOn;

    @Column(name="CREATED_BY")
    private String createdBy;




}