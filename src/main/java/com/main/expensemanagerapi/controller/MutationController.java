package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.Account;
import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.enums.AccountType;
import com.main.expensemanagerapi.repository.AccountEntityRepository;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;
import java.util.UUID;

@RestController
public class MutationController {

    @Autowired
    private OrganizationEntityRepository organizationEntityRepository;
    @Autowired
    private AccountEntityRepository accountEntityRepository;

    @PostMapping("/register")
    public String register() {
        String organizationId = UUID.randomUUID().toString();
        String userSub = UUID.randomUUID().toString();
        Organization organization = new Organization(organizationId, userSub);
        organizationEntityRepository.save(organization);
        return organizationId;
    }

    @PostMapping("/create-account")
    public String createAccount() {
        String organizationId = "97ce2f91-5e2f-4381-83f2-87b835bd0cad";
        String userSub = "8d1a0d59-79a0-4430-a813-328887e35cc9";
        String accountId = UUID.randomUUID().toString();

        Account account = new Account(accountId, organizationId, 0L, userSub, Currency.getInstance("CAD"), "Scotiabank Savings Account", AccountType.SAVINGS);
        this.accountEntityRepository.save(account);
        return accountId;
    }
}
