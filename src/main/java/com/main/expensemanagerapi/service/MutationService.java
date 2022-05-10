package com.main.expensemanagerapi.service;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.enums.AccountType;
import com.main.expensemanagerapi.repository.AccountRecordEntityRepository;
import com.main.expensemanagerapi.repository.CategoryRepository;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import com.main.expensemanagerapi.repository.accountEntity.AccountEntityRepository;
import com.main.expensemanagerapi.types.CreateAccount;
import com.main.expensemanagerapi.types.CreateAccountRecord;
import com.main.expensemanagerapi.types.UpdateAccountRecord;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MutationService {
    private final AccountEntityRepository accountEntityRepository;
    private final AccountRecordEntityRepository accountRecordEntityRepository;
    private final CategoryRepository categoryRepository;
    private final OrganizationEntityRepository organizationEntityRepository;

    @Autowired
    public MutationService(
        AccountEntityRepository accountEntityRepository,
        AccountRecordEntityRepository accountRecordEntityRepository,
        CategoryRepository categoryRepository,
        OrganizationEntityRepository organizationEntityRepository
    ) {
        this.organizationEntityRepository = organizationEntityRepository;
        this.accountEntityRepository = accountEntityRepository;
        this.categoryRepository = categoryRepository;
        this.accountRecordEntityRepository = accountRecordEntityRepository;
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
                "SYSTEM",
                Currency.getInstance("USD"),
                "Savings Account",
                AccountType.SAVINGS
        );

        Account generalAccount = new Account(
                UUID.randomUUID().toString(),
                organizationId,
                "SYSTEM",
                Currency.getInstance("USD"),
                "General Account",
                AccountType.GENERAL
        );

        accountEntityRepository.save(savingsAccount);
        accountEntityRepository.save(generalAccount);
        return organizationId;
    }

    public List<Account> findAccounts(String organizationId, String userSub) {
        String storedOrganizationId = this.organizationEntityRepository.getByUserSub(userSub).getId();
        if (!Objects.equals(storedOrganizationId, organizationId)) throw new InvalidRequestStateException("Organization does not belongs to this user");
        return this.accountEntityRepository.findByOrganizationId(organizationId);
    }

    public String createAccount(String userSub, CreateAccount createAccount) {
        Account account = new Account(
                UUID.randomUUID().toString(),
                createAccount.getOrganizationId(),
                userSub,
                Currency.getInstance(createAccount.getCurrency()),
                createAccount.getName(),
                createAccount.getType()
        );
        this.accountEntityRepository.save(account);
        return account.getId();
    }

    public List<Category> findCategories() {
        return this.categoryRepository.findAll();
    }

    public String createRecord(String userSub, CreateAccountRecord createAccountRecord) {
        AccountRecord accountRecord = new AccountRecord(
                UUID.randomUUID().toString(),
                createAccountRecord.getCategoryId(),
                createAccountRecord.getAmount(),
                Currency.getInstance(createAccountRecord.getCurrency()),
                new Date(),
                createAccountRecord.getNote(),
                createAccountRecord.getPayee(),
                createAccountRecord.getFromAccountId(),
                createAccountRecord.getToAccountId(),
                createAccountRecord.getType()
        );
        this.accountRecordEntityRepository.save(accountRecord);
        return accountRecord.getId();
    }

    public List<AccountRecord> findRecords(String organizationId, String userSub) {
        return this.accountRecordEntityRepository.findByOrganization(organizationId);
    }

    public String updateRecord(String userSub, UpdateAccountRecord updateAccountRecord) {
        AccountRecord accountRecord = new AccountRecord(
                UUID.randomUUID().toString(),
                updateAccountRecord.getCategoryId(),
                updateAccountRecord.getAmount(),
                Currency.getInstance(updateAccountRecord.getCurrency()),
                new Date(),
                updateAccountRecord.getNote(),
                updateAccountRecord.getPayee(),
                updateAccountRecord.getFromAccountId(),
                updateAccountRecord.getToAccountId(),
                updateAccountRecord.getType()
        );
        this.accountRecordEntityRepository.save(accountRecord);
        return accountRecord.getId();
    }
}
