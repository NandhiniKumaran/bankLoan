package com.demo.bank.data;

import com.demo.bank.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    List<Role> findAll();
}
