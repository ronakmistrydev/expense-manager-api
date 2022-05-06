package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.Account;
import com.main.expensemanagerapi.domain.AccountTransaction;
import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.enums.AccountType;
import com.main.expensemanagerapi.enums.TransactionType;
import com.main.expensemanagerapi.repository.AccountEntityRepository;
import com.main.expensemanagerapi.repository.AccountTransactionEntityRepository;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import com.main.expensemanagerapi.service.MutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

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

    @PostMapping("/create-account")
    public String createAccount() {
        String organizationId = "97ce2f91-5e2f-4381-83f2-87b835bd0cad";
        String userSub = "8d1a0d59-79a0-4430-a813-328887e35cc9";
        String accountId = UUID.randomUUID().toString();
        Account account = new Account(
            accountId,
            organizationId,
            0L,
            userSub,
            Currency.getInstance("CAD"),
            "Scotiabank Savings Account",
            AccountType.SAVINGS
        );
        this.accountEntityRepository.save(account);
        return accountId;
    }

    @PostMapping("/add-transaction")
    public String addTransaction() {
        String accountId = "bb6365b6-54c7-4db0-a42a-f3c1eee211b9";
        String categoryId = "2f02c58a-7f32-42cd-86c0-506f459a866c"; //Food & Drinks
        String accountTransactionId = UUID.randomUUID().toString();
        AccountTransaction accountTransaction = new AccountTransaction(
            accountTransactionId,
            categoryId,
            new BigDecimal(10.37),
            Currency.getInstance("CAD"),
            new Date(),
            "My First Transaction",
            "Tim Hortons",
            accountId,
            null,
            TransactionType.EXPENSE
        );

        accountTransactionEntityRepository.save(accountTransaction);
        return accountTransactionId;
    }
}
