package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.service.MutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QueryController {
    private final MutationService mutationService;

    @Autowired
    public QueryController(MutationService mutationService) {
        this.mutationService = mutationService;
    }

    @GetMapping("/accountsByOrganization")
    public List<Account> accountsByOrganization(Authentication authentication, @RequestParam final String organizationId) {
        return this.mutationService.findAccounts(organizationId, authentication.getName());
    }

    @GetMapping("/categories")
    public List<Category> categories() {
        return this.mutationService.findCategories();
    }

    @GetMapping("/records")
    public List<AccountRecord> categories(Authentication authentication, @RequestParam final String organizationId) {
        return this.mutationService.findRecords(organizationId, authentication.getName());
    }
}
