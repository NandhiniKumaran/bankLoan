package com.demo.bank.controller;

import com.demo.bank.config.MessageProperties;
import com.demo.bank.dto.CreditDto;
import com.demo.bank.dto.UserDto;
import com.demo.bank.entity.User;
import com.demo.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
    public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    private MessageProperties messageProperties;

    // handler method to handle user registration form submit request
    @Secured("ROLE_ADMIN")
    @PostMapping("/register/save")
    public ResponseEntity<UserDto> registration (@Valid @RequestBody UserDto userDto){
        HttpHeaders headers = new HttpHeaders();
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            headers.add("failureMessage", messageProperties.getUserConflictResponse());
           return new ResponseEntity<>(userDto,headers,HttpStatus.CONFLICT);
        }
        userDto=userService.saveUser(userDto);
        headers.add("successMessage", messageProperties.getUserSuccessResponse());
        return ResponseEntity.created(URI.create("/api/register/save")).headers(headers).body(userDto);
    }

    }

