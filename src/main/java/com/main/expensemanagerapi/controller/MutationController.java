package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.domain.account.Account;
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
    private final MutationService mutationService;

    @Autowired
    public MutationController(MutationService mutationService) {
        this.mutationService = mutationService;
    }

    @PostMapping("/register")
    public String register(Authentication authentication) {
        return mutationService.register(authentication.getName());
    }

    @GetMapping("/accountsByOrganization")
    public List<Account> accountsByOrganization(Authentication authentication, @RequestParam final String organizationId) {
        return this.mutationService.findAccounts(organizationId, authentication.getName());
    }

    @PostMapping("/createAccount")
    public String createAccount(Authentication authentication, @RequestBody CreateAccount createAccount) {
        return this.mutationService.createAccount(authentication.getName(), createAccount);
    }

    @GetMapping("/categories")
    public List<Category> categories() {
        return this.mutationService.findCategories();
    }

    @PostMapping("/createRecord")
    public String createAccount(
        Authentication authentication,
        @RequestBody CreateAccountRecord accountRecord,
        @RequestParam final String organizationId
    ) {
        return this.mutationService.createRecord(organizationId, authentication.getName(), accountRecord);
    }

    @PutMapping("/updateRecord")
    public String createAccount(Authentication authentication, @RequestBody UpdateAccountRecord updateAccountRecord) {
        return this.mutationService.updateRecord(authentication.getName(), updateAccountRecord);
    }

    @GetMapping("/records")
    public List<AccountRecord> categories(Authentication authentication, @RequestParam final String organizationId) {
        return this.mutationService.findRecords(organizationId, authentication.getName());
    }

}
