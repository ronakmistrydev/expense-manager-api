package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.Account;
import com.main.expensemanagerapi.enums.AccountType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Currency;
import java.util.Date;

@Document("account")
public class AccountEntity extends RootEntity {

    private final String organizationId;
    private final String createdBy;
    private Currency currency;
    private String name;
    private AccountType type;

    public AccountEntity(String id, String organizationId, String createdBy, Currency currency, String name, AccountType type) {
        super(id);
        this.organizationId = organizationId;
        this.createdBy = createdBy;
        this.currency = currency;
        this.name = name;
        this.type = type;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public static AccountEntity toEntity(Account account) {
        return new AccountEntity(
            account.getId(),
            account.getOrganizationId(),
            account.getCreatedBy(),
            account.getCurrency(),
            account.getName(),
            account.getType()
        );
    }
}
