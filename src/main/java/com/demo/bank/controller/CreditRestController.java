package com.demo.bank.controller;

import java.util.List;


import com.demo.bank.entity.Credit;
import com.demo.bank.service.CreditService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class CreditRestController {
    private final CreditService creditService;

    public CreditRestController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<Credit> getAllCreditAccounts(){
        return creditService.getAllCredits();
    }
}
