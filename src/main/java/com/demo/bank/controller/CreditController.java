package com.demo.bank.controller;


import com.demo.bank.entity.Credit;
import com.demo.bank.service.CreditService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/accounts")
public class CreditController {

   private final CreditService creditService;
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public String getAllCreditAccounts(Model model){
        model.addAttribute("creditAccs", creditService.getAllCredits());
        return "credit";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/new")
    public String createNewCreditAccounts(Credit credit){
        return "newCredit";
    }

    @PostMapping
    public String setNewCreditAccounts(@Valid Credit newAccount, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "newCredit";
        }

        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        newAccount.setCreatedBy(user.getUsername());
        newAccount.setCreatedOn(LocalDate.now());

        creditService.saveNewCreditAccount(newAccount);
        return "redirect:/accounts";
    }
}
