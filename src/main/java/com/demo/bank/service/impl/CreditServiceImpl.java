package com.demo.bank.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.demo.bank.data.CreditRepository;
import com.demo.bank.data.RoleRepository;
import com.demo.bank.dto.CreditDto;
import com.demo.bank.dto.CustomUser;
import com.demo.bank.entity.Credit;
import com.demo.bank.dto.RoleList;
import com.demo.bank.entity.User;
import com.demo.bank.service.CreditService;
import com.demo.bank.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository ;
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    private Environment env;

    @Override
    public List<CreditDto> getAllCredits(){
        List<Credit> creditList= creditRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return Arrays.asList(mapper.map(creditList, CreditDto[].class));

    }
    @Override
    public CreditDto saveNewCreditAccount(CreditDto creditDto){
        creditDto.getUser().setPassword(env.getProperty("default.password"));
        creditDto.getUser().setRole(RoleList.ROLE_CUSTOMER.toString());

        creditDto.setUser(userService.saveUser(creditDto.getUser()));

        //User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser user=userService.getCurrentUser();

        ModelMapper mapper=new ModelMapper();
        Credit credit=mapper.map(creditDto,Credit.class);

        credit.setCreatedBy(user.getUsername());
        credit.setCreatedOn(LocalDate.now());
        creditRepository.save(credit);
        creditDto.setId(credit.getId());
        return creditDto;
    }
    @Override
    public Credit retrieveCreditAccount(long id){
        Credit creditAccObj = null;
        Optional<Credit> creditAcc = creditRepository.findById(id);
        if(creditAcc.isPresent()){
            creditAccObj= creditAcc.get();
        }
        return creditAccObj;
    }

    @Override
    public Credit retrieveCreditByUser(long id){
        User user= userService.findById(id);
        return creditRepository.findByUser(user);

    }

}