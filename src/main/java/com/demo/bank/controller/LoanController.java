package com.demo.bank.controller;


import com.demo.bank.config.MessageProperties;
import com.demo.bank.dto.LoanDto;
import com.demo.bank.entity.Credit;
import com.demo.bank.entity.Loan;
import com.demo.bank.service.CreditService;
import com.demo.bank.service.LoanService;
import com.demo.bank.service.impl.CreditServiceImpl;
import com.demo.bank.service.impl.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    LoanService loanService;
    @Autowired
    CreditService creditService;
    @Autowired
    MessageProperties messageProperties;





    @Secured("ROLE_ADMIN")
    @PostMapping (value = "/new")
    public ResponseEntity<LoanDto> saveNewCreditAccounts (@RequestBody LoanDto newLoan){
        HttpHeaders headers = new HttpHeaders();
      try {
          newLoan = loanService.saveLoanForAccount(newLoan);
          headers.add("successMessage", messageProperties.getLoanSuccessResponse());
          return ResponseEntity.created(URI.create("/api/loan/new")).headers(headers).body(newLoan);
      }catch (Exception e)
      {
          headers.add("failureMessage", messageProperties.getLoanFailureResponse());
          return new ResponseEntity<>(headers,HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @GetMapping(value = "/retrieveLoan/{accountNo}")
    public ResponseEntity<List<LoanDto>> getAllLoan(@PathVariable long accountNo){
        List<LoanDto> loanDtoList= loanService.getLoanByAccountNo(accountNo);
        return new ResponseEntity<>(loanDtoList,HttpStatus.OK);
    }

}
