package com.demo.bank.dto;

import com.demo.bank.entity.Credit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {


    private long loanId;
    @NotNull(message = "loan should not be null")
    @NotEmpty(message = "Loan type is mandatory")
    private LoanType loanType;
    @DecimalMin(value = "500.0", message = "Total limit is mandatory")
    private double amount;
    @NotBlank(message = "Currency is mandatory")
    private String currency;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    @DecimalMin(value = "1.0", message = "Limit is mandatory")
    private double interest;
    private String paymentStatus;
    private CreditDto credit;
}
