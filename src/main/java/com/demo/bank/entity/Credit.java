package com.demo.bank.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name="CREDIT_ACCOUNT")
public class Credit {

    @Id
    @Column(name="ACC_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="APPLICANT_NAME")
    @NotBlank(message = "Name is mandatory")
    private String applicantName;

    @Column(name="TOTAL_LIMIT")
    @DecimalMin(value = "1000.0", message = "Total limit is mandatory")
    private double totalLimit;

    @Column(name="CURRENCY")
    @NotBlank(message = "Currency is mandatory")
    private String currency;

    @Column(name="START_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
   // @NotBlank(message = "Start date is mandatory")
    private LocalDate startDate;

    @Column(name="END_DATE")
    //@NotBlank(message = "End Date is mandatory")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name="TOTAL_LIMIT_HOME")
    @DecimalMin(value = "1000.0", message = "Home limit is mandatory")
    private double totalLimitHome;

    @Column(name="TOTAL_LIMIT_CAR")
    @DecimalMin(value = "1000.0", message = "Home limit is mandatory")
    private double totalLimitCar;

    @Column(name="CREATED_ON")
    private LocalDate createdOn;

    @Column(name="CREATED_BY")
    private String createdBy;


    public Credit() {
    }


    public Credit(long id, String applicantName, double totalLimit, String currency, LocalDate startDate, LocalDate endDate, double totalLimitHome, double totalLimitCar, LocalDate createdOn, String createdBy) {
        this.id = id;
        this.applicantName = applicantName;
        this.totalLimit = totalLimit;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalLimitHome = totalLimitHome;
        this.totalLimitCar = totalLimitCar;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public double getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(double totalLimit) {
        this.totalLimit = totalLimit;
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

    public double getTotalLimitHome() {
        return totalLimitHome;
    }

    public void setTotalLimitHome(double totalLimitHome) {
        this.totalLimitHome = totalLimitHome;
    }

    public double getTotalLimitCar() {
        return totalLimitCar;
    }

    public void setTotalLimitCar(double totalLimitCar) {
        this.totalLimitCar = totalLimitCar;
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
