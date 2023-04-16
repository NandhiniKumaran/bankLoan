package com.demo.bank.data;


import com.demo.bank.entity.Credit;
import com.demo.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {

    Credit findByUser(User user);
}
