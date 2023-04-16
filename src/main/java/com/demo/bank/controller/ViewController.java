package com.demo.bank.controller;

import com.demo.bank.dto.*;
import com.demo.bank.entity.Role;
import com.demo.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ViewController {

    @Autowired
    UserService userService;

    @RequestMapping("/default")
    public String defaultAfterLogin() {
        //User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser user=userService.getCurrentUser();
        if (user.getAuthorities().stream().anyMatch(obj -> obj.getAuthority().contains("ROLE_ADMIN"))) {
            return "redirect:/home/";
        }
        return "redirect:/loan/retrieveLoan/"+user.getUserID();
    }


    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/register")
    public ModelAndView viewRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList", RoleList.values());
        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/loan/new/{accountNo}")
    public ModelAndView viewAccountList(@PathVariable long accountNo){
        Map<String,Object> request=new HashMap<>();
        CreditDto credit=new CreditDto();
        credit.setId(accountNo);
        ModelAndView modelAndView = new ModelAndView();
        request.put("credit",credit);
        request.put("loanType",LoanType.values());
        //modelAndView.addAllObjects("creditId",accountNo);
        modelAndView.addAllObjects(request);
        modelAndView.setViewName("newLoan.html");
        return modelAndView;
    }

    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @GetMapping(value = "/loan/retrieveLoan/{accountNo}")
    public ModelAndView viewLoanList(@PathVariable long accountNo){
        Map<String,Object> request=new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        request.put("accountNo",accountNo);
        modelAndView.addAllObjects(request);
        modelAndView.setViewName("loan.html");
        return modelAndView;
    }

    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @GetMapping(value = "/loanPayment/new/{loanId}")
    public ModelAndView viewLoanPayment(@PathVariable long loanId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loanId",loanId);
        modelAndView.setViewName("newPayment.html");
        return modelAndView;
    }

    @Secured({"ROLE_ADMIN","ROLE_CUSTOMER"})
    @GetMapping(value = "/loanPayment/retrievePaymentDetails/{loanId}")
    public ModelAndView viewLoanPaymentList(@PathVariable long loanId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loanId",loanId);
        modelAndView.setViewName("payments.html");
        return modelAndView;
    }



}
