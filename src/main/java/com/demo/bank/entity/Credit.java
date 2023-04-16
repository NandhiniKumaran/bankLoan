package com.demo.bank.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="CREDIT_ACCOUNT")
public class Credit {

    @Id
    @Column(name="ACC_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="TOTAL_LIMIT")
    private double totalLimit;

    @Column(name="CURRENCY")
    private String currency;

    @Column(name="START_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name="END_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name="TOTAL_LIMIT_HOME")
    private double totalLimitHome;

    @Column(name="TOTAL_LIMIT_CAR")
    private double totalLimitCar;

    @Column(name="CREATED_ON")
    private LocalDate createdOn;

    @Column(name="CREATED_BY")
    private String createdBy;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;


}
