package com.demo.bank.dto;

import com.demo.bank.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanPaymentDetailsDto {

    private long paymentNo;
    @NotNull(message = "paidAmount should not be null")
    private double paidAmount;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate paidDate;
    private String remarks;
    private Boolean isDeleted;
    private LoanDto loan;





}