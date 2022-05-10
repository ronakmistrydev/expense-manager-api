package com.main.expensemanagerapi.domain.account;

import com.main.expensemanagerapi.enums.AccountType;

import java.math.BigDecimal;
import java.util.Currency;

public class Account {
    private final String id;
    private final String organizationId;
    private final BigDecimal balance;
    private final String createdBy;
    private final Currency currency;
    private final String name;
    private final AccountType type;

    public Account(
            String id,
            String organizationId,
            String createdBy,
            Currency currency,
            String name,
            AccountType type
    ) {
        this.id = id;
        this.organizationId = organizationId;
        this.createdBy = createdBy;
        this.currency = currency;
        this.name = name;
        this.type = type;
        this.balance = BigDecimal.ZERO;
    }

    public Account(
        String id,
        String organizationId,
        BigDecimal balance,
        String createdBy,
        Currency currency,
        String name,
        AccountType type
    ) {
        this.id = id;
        this.organizationId = organizationId;
        this.balance = balance;
        this.createdBy = createdBy;
        this.currency = currency;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public BigDecimal getBalance() {
        return balance;
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
