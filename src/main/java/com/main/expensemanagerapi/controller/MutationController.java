package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.Account;
import com.main.expensemanagerapi.repository.AccountEntityRepository;
import com.main.expensemanagerapi.repository.AccountTransactionEntityRepository;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import com.main.expensemanagerapi.service.MutationService;
import com.main.expensemanagerapi.types.CreateAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MutationController {
    private final OrganizationEntityRepository organizationEntityRepository;
    private final AccountEntityRepository accountEntityRepository;
    private final AccountTransactionEntityRepository accountTransactionEntityRepository;

    private final MutationService mutationService;

    @Autowired
    public MutationController(
        OrganizationEntityRepository organizationEntityRepository,
        AccountEntityRepository accountEntityRepository,
        AccountTransactionEntityRepository accountTransactionEntityRepository,
        MutationService mutationService
    ) {
        this.organizationEntityRepository = organizationEntityRepository;
        this.accountEntityRepository = accountEntityRepository;
        this.accountTransactionEntityRepository = accountTransactionEntityRepository;
        this.mutationService = mutationService;
    }

    @GetMapping("/hello")
    public String greetings(Authentication authentication) {
        return authentication.getName();
    }

    @PostMapping("/register")
    public String register(Authentication authentication) {
        return mutationService.register(authentication.getName());
    }

    @GetMapping("/accounts-by-organization")
    public List<Account> accountsByOrganization(Authentication authentication, @RequestParam final String organizationId) {
        return this.mutationService.findAccounts(organizationId, authentication.getName());
    }

    @PostMapping("/create-account")
    public String createAccount(Authentication authentication, @RequestBody CreateAccount createAccount) {
        return this.mutationService.createAccount(authentication.getName(), createAccount);
    }
}
