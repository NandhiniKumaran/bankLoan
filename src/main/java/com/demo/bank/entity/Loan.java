package com.demo.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

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
    @DecimalMin(value = "500.0", message = "Total limit is mandatory")
    private double amount;

    @Column(name="CURRENCY")
    @NotBlank(message = "Currency is mandatory")
    private String currency;

    @Column(name="START_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name="END_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name="INTEREST_RATE")
    @DecimalMin(value = "1.0", message = "Limit is mandatory")
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

    public Loan() {
    }

    public Loan(long loanId,LoanType loanType,double amount,String currency,LocalDate startDate,LocalDate endDate,double interest,Credit credit) {
        this.loanId=loanId;
        this.loanType=loanType;
        this.amount=amount;
        this.currency=currency;
        this.startDate=startDate;
        this.endDate=endDate;
        this.interest=interest;
        this.credit=credit;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}