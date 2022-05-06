package com.main.expensemanagerapi.service;

import com.main.expensemanagerapi.domain.Account;
import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.enums.AccountType;
import com.main.expensemanagerapi.repository.AccountEntityRepository;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.UUID;

@Service
public class MutationService {
    private final OrganizationEntityRepository organizationEntityRepository;
    private final AccountEntityRepository accountEntityRepository;

    @Autowired
    public MutationService(
            OrganizationEntityRepository organizationEntityRepository,
            AccountEntityRepository accountEntityRepository
    ) {
        this.organizationEntityRepository = organizationEntityRepository;
        this.accountEntityRepository = accountEntityRepository;
    }

    public String register(String userSub) {
        boolean isAlreadyExists = this.organizationEntityRepository.hasOrganizationByUserSub(userSub);
        if (isAlreadyExists) return this.organizationEntityRepository.getByUserSub(userSub).getId();

        String organizationId = UUID.randomUUID().toString();
        Organization organization = new Organization(organizationId, userSub);
        organizationEntityRepository.save(organization);

        Account savingsAccount = new Account(
                UUID.randomUUID().toString(),
                organizationId,
                0L,
                "SYSTEM",
                Currency.getInstance("USD"),
                "Savings Account",
                AccountType.SAVINGS
        );

        Account generalAccount = new Account(
                UUID.randomUUID().toString(),
                organizationId,
                0L,
                "SYSTEM",
                Currency.getInstance("USD"),
                "General Account",
                AccountType.GENERAL
        );

        accountEntityRepository.save(savingsAccount);
        accountEntityRepository.save(generalAccount);
        return organizationId;
    }
}
