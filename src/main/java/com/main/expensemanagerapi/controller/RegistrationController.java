package com.main.expensemanagerapi.controller;

import com.main.expensemanagerapi.domain.Account;
import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.domain.User;
import com.main.expensemanagerapi.repository.InMemoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final InMemoryRepository accountRepository = new InMemoryRepository<Account>();
    private final InMemoryRepository organizationRepository = new InMemoryRepository<Organization>();
    private final InMemoryRepository userRepository = new InMemoryRepository<User>();

    @PostMapping
    public String register() {
        String organizationId = "create-organization-"+ new Date().getTime();

        Organization organization = new Organization().create(organizationId);
        this.organizationRepository.save(organizationId, organization);


        String userId = "create-user-"+ new Date().getTime();
        String userSub = "create-user-sub-"+ new Date().getTime();
        this.userRepository.save(userId, new User().create(userId, organizationId, userSub));


        String accountId1 = "create-account-"+ new Date().getTime();
        String accountId2 = "create-account-"+ new Date().getTime();

        Account account1 = new Account().create(accountId1, 1000L, "Savings Account", "CAD", organizationId);
        Account account2 = new Account().create(accountId2, 1000L, "Current Account", "CAD", organizationId);

        this.accountRepository.save(accountId1, account1);
        this.accountRepository.save(accountId2, account2);


        return organizationId;
    }
}
