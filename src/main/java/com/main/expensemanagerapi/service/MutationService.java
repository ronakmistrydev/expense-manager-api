package com.main.expensemanagerapi.service;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.enums.AccountType;
import com.main.expensemanagerapi.enums.RecordType;
import com.main.expensemanagerapi.repository.accountEntity.AccountEntityRepository;
import com.main.expensemanagerapi.repository.accountRecordEntity.AccountRecordEntityRepository;
import com.main.expensemanagerapi.repository.categoryEntity.CategoryEntityRepository;
import com.main.expensemanagerapi.repository.organizationEntity.OrganizationEntityRepository;
import com.main.expensemanagerapi.types.CreateAccount;
import com.main.expensemanagerapi.types.CreateAccountRecord;
import com.main.expensemanagerapi.types.UpdateAccountRecord;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MutationService {
    private final AccountEntityRepository accountEntityRepository;
    private final AccountRecordEntityRepository accountRecordEntityRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final OrganizationEntityRepository organizationEntityRepository;

    @Autowired
    public MutationService(
        AccountEntityRepository accountEntityRepository,
        AccountRecordEntityRepository accountRecordEntityRepository,
        CategoryEntityRepository categoryEntityRepository,
        OrganizationEntityRepository organizationEntityRepository
    ) {
        this.accountEntityRepository = accountEntityRepository;
        this.accountRecordEntityRepository = accountRecordEntityRepository;
        this.categoryEntityRepository = categoryEntityRepository;
        this.organizationEntityRepository = organizationEntityRepository;
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
                BigDecimal.ZERO,
                "SYSTEM",
                Currency.getInstance("USD"),
                "Savings Account",
                AccountType.SAVINGS
        );

        Account generalAccount = new Account(
                UUID.randomUUID().toString(),
                organizationId,
                BigDecimal.ZERO,
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
        this.throwIfInvalidOrganization(organizationId, userSub);
        return this.accountEntityRepository.findByOrganizationId(organizationId);
    }

    public String createAccount(String userSub, CreateAccount createAccount) {
        this.throwIfInvalidOrganization(createAccount.getOrganizationId(), userSub);
        String accountId = UUID.randomUUID().toString();
        Account account = new Account(
                accountId,
                createAccount.getOrganizationId(),
                userSub,
                Currency.getInstance(createAccount.getCurrency()),
                createAccount.getName(),
                createAccount.getType()
        );
        this.accountEntityRepository.save(account);

        // TODO:: dynamic category id.
        String accountRecordId = UUID.randomUUID().toString();
        String incomeCategoryId = "6fb109ec-a3cb-4b6d-ae9a-52fd53995840";

        AccountRecord accountRecord = new AccountRecord(
                accountRecordId,
                incomeCategoryId,
                createAccount.getBalance(),
                Currency.getInstance(createAccount.getCurrency()),
                new Date(),
                "Account created",
                "",
                accountId,
                null,
                RecordType.TRANSFER
        );

        this.accountRecordEntityRepository.save(accountRecord);

        return accountId;
    }

    public List<Category> findCategories() {
        return this.categoryEntityRepository.findAll();
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
        return this.accountRecordEntityRepository.findByOrganizationId(organizationId);
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

    private void throwIfInvalidOrganization(final String organizationId, final String userSub) {
        String dbOrganizationId = this.organizationEntityRepository.getByUserSub(userSub).getId();
        if (!Objects.equals(dbOrganizationId, organizationId)) throw new InvalidRequestStateException("Organization does not belongs to this user");
    }
}
