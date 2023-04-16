package com.demo.bank.service.impl;

import com.demo.bank.data.RoleRepository;
import com.demo.bank.data.UserRepository;
import com.demo.bank.dto.CustomUser;
import com.demo.bank.dto.UserDto;
import com.demo.bank.entity.Role;
import com.demo.bank.dto.RoleList;
import com.demo.bank.entity.User;
import com.demo.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName(userDto.getRole());
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    private Role checkRoleExist(){
        Role role = new Role();
        role.setName(RoleList.ROLE_ADMIN.toString());
        return roleRepository.save(role);
    }
    @Override
    public CustomUser getCurrentUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (CustomUser)authentication.getPrincipal();
    }

    @Override
    public  User findById(long id)
    {
        return userRepository.findById(id);
    }
}
