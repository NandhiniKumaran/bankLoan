package com.demo.bank.service;

import com.demo.bank.dto.CustomUser;
import com.demo.bank.dto.UserDto;
import com.demo.bank.entity.User;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    User findUserByEmail(String email);
    CustomUser getCurrentUser();
    User findById(long id);

}