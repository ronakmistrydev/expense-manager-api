package com.main.expensemanagerapi.service;

import com.main.expensemanagerapi.domain.AccountTransaction;
import com.main.expensemanagerapi.domain.Category;
import com.main.expensemanagerapi.domain.Organization;
import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.enums.AccountType;
import com.main.expensemanagerapi.repository.AccountEntityRepository;
import com.main.expensemanagerapi.repository.AccountTransactionEntityRepository;
import com.main.expensemanagerapi.repository.CategoryRepository;
import com.main.expensemanagerapi.repository.OrganizationEntityRepository;
import com.main.expensemanagerapi.types.CreateAccount;
import com.main.expensemanagerapi.types.CreateAccountRecord;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MutationService {
    private final OrganizationEntityRepository organizationEntityRepository;
    private final AccountEntityRepository accountEntityRepository;
    private final CategoryRepository categoryRepository;

    private final AccountTransactionEntityRepository accountTransactionEntityRepository;

    @Autowired
    public MutationService(
        OrganizationEntityRepository organizationEntityRepository,
        AccountEntityRepository accountEntityRepository,
        CategoryRepository categoryRepository,
        AccountTransactionEntityRepository accountTransactionEntityRepository
    ) {
        this.organizationEntityRepository = organizationEntityRepository;
        this.accountEntityRepository = accountEntityRepository;
        this.categoryRepository = categoryRepository;
        this.accountTransactionEntityRepository = accountTransactionEntityRepository;
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

        AccountTransaction accountTransaction = new AccountTransaction(
                UUID.randomUUID().toString(),
                createAccountRecord.getCategoryId(),
                createAccountRecord.getAmount(),
                Currency.getInstance(createAccountRecord.getCurrency()),
                createAccountRecord.getDate(),
                createAccountRecord.getNote(),
                createAccountRecord.getPayee(),
                createAccountRecord.getFromAccountId(),
                createAccountRecord.getToAccountId(),
                createAccountRecord.getType()
        );
        this.accountTransactionEntityRepository.save(accountTransaction);
        return accountTransaction.getId();
    }

    public void findRecords() {}

    public void updateRecord() {}


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
