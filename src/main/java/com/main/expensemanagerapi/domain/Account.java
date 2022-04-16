package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.enums.AccountType;

import java.util.Currency;

public class Account {
    private final String id;
    private final String organizationId;
    private final String createdBy;
    private Currency currency;
    private String name;
    private AccountType type;

    public Account(
        String id,
        String organizationId,
        Long balance,
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
    }

    public String getId() {
        return id;
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
