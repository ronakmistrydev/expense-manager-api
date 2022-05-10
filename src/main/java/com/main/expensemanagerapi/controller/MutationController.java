package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.AccountTransaction;
import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.repository.AccountEntityRepository;
import com.main.expensemanagerapi.repository.AccountTransactionEntityRepository;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import com.main.expensemanagerapi.service.MutationService;
import com.main.expensemanagerapi.types.CreateAccount;
import com.main.expensemanagerapi.types.CreateAccountRecord;
import com.main.expensemanagerapi.types.UpdateAccountRecord;
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

    @GetMapping("/categories")
    public List<Category> categories() {
        return this.mutationService.findCategories();
    }

    @PostMapping("/create-record")
    public String createAccount(Authentication authentication, @RequestBody CreateAccountRecord accountTransaction) {
        return this.mutationService.createRecord(authentication.getName(), accountTransaction);
    }

    @PutMapping("/update-record")
    public String createAccount(Authentication authentication, @RequestBody UpdateAccountRecord updateAccountRecord) {
        return this.mutationService.updateRecord(authentication.getName(), updateAccountRecord);
    }

    @GetMapping("/records")
    public List<AccountTransaction> categories(Authentication authentication, @RequestParam final String organizationId) {
        return this.mutationService.findRecords(organizationId, authentication.getName());
    }

}
