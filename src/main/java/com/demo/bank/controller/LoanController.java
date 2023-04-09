package com.demo.bank.controller;


import com.demo.bank.entity.Credit;
import com.demo.bank.entity.Loan;
import com.demo.bank.service.CreditService;
import com.demo.bank.service.LoanService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/loan")
public class LoanController {

   private final LoanService loanService;
    private final CreditService creditService;
    public LoanController(CreditService creditService, LoanService loanService) {
        this.loanService = loanService;
        this.creditService = creditService;
    }


    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/new/{accountNo}")
    public String createNewCreditAccounts(Loan loan,@PathVariable long accountNo){
        Credit creditAccount = creditService.retrieveCreditAccount(accountNo);
        loan.setCredit(creditAccount);
        return "newLoan";

    }

    @Secured("ROLE_ADMIN")
    @PostMapping (value = "/new")
    public String saveNewCreditAccounts(Loan newLoan){
        newLoan.setCreatedOn(LocalDate.now());
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newLoan.setCreatedBy(user.getUsername());
        loanService.saveLoanForAccount(newLoan);
         return "redirect:/accounts";

    }
    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/retrieveLoan/{accountNo}")
    public String getAllCreditAccounts(Model model,@PathVariable long accountNo){
        model.addAttribute("loans", loanService.getLoanByAccountNo(accountNo));
        return "loan";
    }







}
