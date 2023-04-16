package com.demo.bank.security;

import com.demo.bank.data.UserRepository;
import com.demo.bank.dto.CustomUser;
import com.demo.bank.dto.RoleList;
import com.demo.bank.entity.Credit;
import com.demo.bank.entity.Role;
import com.demo.bank.entity.User;
import com.demo.bank.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    CreditService creditService;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user.getRoles().stream().anyMatch(obj->obj.getName().equalsIgnoreCase(RoleList.ROLE_CUSTOMER.toString())))
        {
            user.setId(creditService.retrieveCreditByUser(user.getId()).getId());

        }

        if (user != null) {

            return new CustomUser(user.getUsername(),
                    user.getPassword(),true, true, true, true,
                    mapRolesToAuthorities(user.getRoles()),user.getId());

           /* return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));*/
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
