package com.demo.bank.service;

import java.util.List;
import java.util.Optional;

import com.demo.bank.data.CreditRepository;
import com.demo.bank.entity.Credit;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    private final CreditRepository creditRepository ;
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public List<Credit> getAllCredits(){
        return creditRepository.findAll();
    }

    public Credit saveNewCreditAccount(Credit newAccount){
        creditRepository.save(newAccount);
        return newAccount;
    }

    public Credit retrieveCreditAccount(long id){
        Credit creditAccObj = null;
        Optional<Credit> creditAcc = creditRepository.findById(id);
        if(creditAcc.isPresent()){
            creditAccObj= creditAcc.get();
        }
        return creditAccObj;
    }

}