package com.demo.bank.controller;


import com.demo.bank.config.MessageProperties;
import com.demo.bank.dto.CreditDto;
import com.demo.bank.entity.User;
import com.demo.bank.service.CreditService;
import com.demo.bank.service.impl.CreditServiceImpl;
import com.demo.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/accounts")
public class CreditController {
    @Autowired
     MessageProperties messageProperties;
    @Autowired
     UserService userService;
    @Autowired
    CreditService creditService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<CreditDto>> getAllCreditAccounts(){
        List<CreditDto> creditDtoList=creditService.getAllCredits();
        return new ResponseEntity<>(creditDtoList,HttpStatus.OK);
    }


    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<CreditDto> createNewCreditAccounts(@Valid @RequestBody CreditDto creditDto) {

        HttpHeaders headers = new HttpHeaders();
        User existingUser = userService.findUserByEmail(creditDto.getUser().getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            headers.add("failureMessage", messageProperties.getUserConflictResponse());
            return new ResponseEntity<>(headers,HttpStatus.CONFLICT);
        }
        creditDto=creditService.saveNewCreditAccount(creditDto);
        headers.add("successMessage", messageProperties.getAccountSuccessResponse());
        return ResponseEntity.created(URI.create("/api/accounts")).headers(headers).body(creditDto);
    }


}
