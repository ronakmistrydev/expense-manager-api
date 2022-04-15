package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.Account;
import com.main.expensemanagerapi.repository.InMemoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final InMemoryRepository accountRepository = new InMemoryRepository<Account>();

    @GetMapping("/")
    List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @PostMapping
    void create() {
        String id = "create-account-"+ new Date().getTime();
    }
}
