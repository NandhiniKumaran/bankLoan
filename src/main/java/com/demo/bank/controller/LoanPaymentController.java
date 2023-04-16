package com.demo.bank.controller;


import com.demo.bank.config.MessageProperties;
import com.demo.bank.dto.LoanDto;
import com.demo.bank.dto.LoanPaymentDetailsDto;
import com.demo.bank.entity.Loan;
import com.demo.bank.entity.LoanPaymentDetails;
import com.demo.bank.service.LoanPaymentDetailsService;
import com.demo.bank.service.LoanService;
import com.demo.bank.service.impl.LoanPaymentDetailsServiceImpl;
import com.demo.bank.service.impl.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/loanPayment")
public class LoanPaymentController {

    @Autowired
    LoanService loanService;
    @Autowired
   LoanPaymentDetailsService loanPaymentDetailsService;

    @Autowired
    MessageProperties messageProperties;

    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @PostMapping(value = "/new")
    public ResponseEntity<LoanPaymentDetailsDto> createNewPayment(@Valid @RequestBody LoanPaymentDetailsDto loanPaymentDetailsDto)
    {
        HttpHeaders headers = new HttpHeaders();

        try {
            //loanPaymentDetails.setDeleted(Boolean.FALSE);
            loanPaymentDetailsDto = loanPaymentDetailsService.saveNewPayment(loanPaymentDetailsDto);

            headers.set("successMessage", messageProperties.getLoanPaymentSuccessResponse());
            return ResponseEntity.created(URI.create("/api/loanPayment/new")).headers(headers).body(loanPaymentDetailsDto);
        }catch (Exception e)
        {
            headers.set("failureMessage", messageProperties.getLoanPaymentFailureResponse());
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @GetMapping(value = "/retrievePaymentDetails/{loanId}")
    public ResponseEntity<List<LoanPaymentDetailsDto>> retrievePaymentDetails(@PathVariable long loanId)
    {
        try {
            List<LoanPaymentDetailsDto> loanPaymentDetailsDtoList=loanPaymentDetailsService.findByLoanId(loanId);
            return new ResponseEntity<>(loanPaymentDetailsDtoList,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}
