package com.demo.bank.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ConfigurationProperties(prefix ="api")
@PropertySource("classpath:message.properties")
@Getter
@Setter
public class MessageProperties {
    private String userSuccessResponse;
    private String userConflictResponse;
    private String accountSuccessResponse;
    private String loanSuccessResponse;
    private String loanFailureResponse;
    private String loanPaymentSuccessResponse;
    private String loanPaymentFailureResponse;


}
