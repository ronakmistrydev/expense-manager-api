package com.main.expensemanagerapi.service;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.enums.AccountType;
import com.main.expensemanagerapi.repository.AccountEntityRepository;
import com.main.expensemanagerapi.repository.AccountRecordEntityRepository;
import com.main.expensemanagerapi.repository.CategoryRepository;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import com.main.expensemanagerapi.types.CreateAccount;
import com.main.expensemanagerapi.types.CreateAccountRecord;
import com.main.expensemanagerapi.types.UpdateAccountRecord;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MutationService {
    private final OrganizationEntityRepository organizationEntityRepository;
    private final AccountEntityRepository accountEntityRepository;
    private final CategoryRepository categoryRepository;

    private final AccountRecordEntityRepository accountRecordEntityRepository;

    @Autowired
    public MutationService(
        OrganizationEntityRepository organizationEntityRepository,
        AccountEntityRepository accountEntityRepository,
        CategoryRepository categoryRepository,
        AccountRecordEntityRepository accountRecordEntityRepository
    ) {
        this.organizationEntityRepository = organizationEntityRepository;
        this.accountEntityRepository = accountEntityRepository;
        this.categoryRepository = categoryRepository;
        this.accountRecordEntityRepository = accountRecordEntityRepository;
    }

    // dfcb6a93-11f4-431c-bcd2-4c906d0323d9 - org id

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
        // todo:: check if organization belongs to this user
        String storedOrganizationId = this.organizationEntityRepository.getByUserSub(userSub).getId();
        if (!Objects.equals(storedOrganizationId, organizationId)) throw new InvalidRequestStateException("Organization does not belongs to this user");
        return this.accountEntityRepository.findByOrganizationId(organizationId);
    }

    public String createAccount(String userSub, CreateAccount createAccount) {
        // todo:: check if organization belongs to this user
        // todo:: check if duplicate - (name, type)
        // todo:: get balance from payload and create initial record under this account
        // todo:: account type specific logic should belongs to sub-class for each type of account (SavingsAccount, CheckIn account etc..)
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
        // todo:: check if to account belongs to given organization
        // todo:: check if given user belongs to an organization
        // todo :: check organization account and user have a relation or connection

        // todo:: fix serialization issue for date
        // todo:: fix created at logic

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
        // todo:: check if account belongs to given organization
        return this.accountRecordEntityRepository.findByOrganization(organizationId);
    }

    public String updateRecord(String userSub, UpdateAccountRecord updateAccountRecord) {
        // todo:: check if account record exists
        // todo:: check if account belongs to given organization
        // todo:: check if user belongs to given organization

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


    /*
     * 4. different versions of account creation
     * 10. add payee
     * 11. update payee
     * 12. get all payee
     * 13. add friends
     * 14. add portion with friends on a record
     *
     *
     * Nice to have
     * 1. update account (name and type)
     * 2. validations
     */
}
