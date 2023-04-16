package com.demo.bank.dto;

import com.demo.bank.entity.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditDto {

    private long id;
    @NotNull(message = "totalLimit should not be null")
    @DecimalMin(value = "1000.0", message = "Total limit should be greater than 1000")
    private double totalLimit;
    @NotNull(message = "totalLimit should not be null")
    @NotEmpty(message = "totalLimit should not be empty")
    private String currency;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    @DecimalMin(value = "1000.0", message = "Home limit should be greater than 1000")
    private double totalLimitHome;
    @DecimalMin(value = "1000.0", message = "Car limit should be greater than 1000")
    private double totalLimitCar;
    @Valid
    private UserDto user;


}
