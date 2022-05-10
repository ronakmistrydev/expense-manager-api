package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.enums.AccountType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Currency;

@Document("account")
public class AccountEntity extends RootEntity {

    private final String organizationId;
    private final String createdBy;
    private final Currency currency;
    private final String name;
    private final AccountType type;

    public AccountEntity(String id, String organizationId, String createdBy, Currency currency, String name, AccountType type) {
        super(id);
        this.organizationId = organizationId;
        this.createdBy = createdBy;
        this.currency = currency;
        this.name = name;
        this.type = type;
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

    public static Account toDomain(AccountEntity entity, BigDecimal balance) {
        return new Account(
                entity.getId(),
                entity.getOrganizationId(),
                balance,
                entity.getCreatedBy(),
                entity.getCurrency(),
                entity.getName(),
                entity.getType()
        );
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public AccountType getType() {
        return type;
    }
}
