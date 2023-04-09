package com.demo.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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



    public LoanPaymentDetails() {
    }

    public LoanPaymentDetails(long paymentNo, double paidAmount, LocalDate paidDate, String remarks, Boolean isDeleted, Loan loan) {
        this.paymentNo = paymentNo;
        this.paidAmount = paidAmount;
        this.paidDate = paidDate;
        this.remarks = remarks;
        this.isDeleted = isDeleted;
        this.loan = loan;
    }

    public long getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(long paymentNo) {
        this.paymentNo = paymentNo;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }



}