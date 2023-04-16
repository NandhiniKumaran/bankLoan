package com.demo.bank.service;

import com.demo.bank.dto.CreditDto;
import com.demo.bank.entity.Credit;

import java.util.List;

public interface CreditService {
    List<CreditDto> getAllCredits();
    CreditDto saveNewCreditAccount(CreditDto creditDto);
    Credit retrieveCreditAccount(long id);
    Credit retrieveCreditByUser(long id);
}
