package com.demo.bank.controller;


import com.demo.bank.entity.Loan;
import com.demo.bank.entity.LoanPaymentDetails;
import com.demo.bank.service.LoanPaymentDetailsService;
import com.demo.bank.service.LoanService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loanPayment")
public class LoanPaymentController {

    private final LoanService loanService;
    private final LoanPaymentDetailsService loanPaymentDetailsService;

    public LoanPaymentController(LoanService loanService, LoanPaymentDetailsService loanPaymentDetailsService) {
        this.loanService = loanService;
        this.loanPaymentDetailsService = loanPaymentDetailsService;
    }
    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @GetMapping(value = "/new/{loanId}")
    public String createNewCreditAccounts(LoanPaymentDetails loanPaymentDetails,@PathVariable long loanId){
        Loan loan = loanService.getLoanById(loanId);
        loanPaymentDetails.setLoan(loan);


        return "newPayment";

    }
    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @PostMapping(value = "/new")
    public String createNewPayment(LoanPaymentDetails loanPaymentDetails)
    {
        loanPaymentDetails.setDeleted(Boolean.FALSE);
        loanPaymentDetailsService.saveNewPayment(loanPaymentDetails);
        return "redirect:/loan/retrieveLoan/"+loanPaymentDetails.getLoan().getCredit().getId();
    }

    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @GetMapping(value = "/retrievePaymentDetails/{loanId}")
    public String retrievePaymentDetails(Model model,@PathVariable long loanId)
    {
        Loan loan=loanService.getLoanById(loanId);
        model.addAttribute("loanPaymentDetails", loanPaymentDetailsService.findByLoan(loan));
        return "payments";

    }


}
