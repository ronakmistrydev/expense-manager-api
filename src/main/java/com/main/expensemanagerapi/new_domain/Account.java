package com.main.expensemanagerapi.new_domain;

import com.main.expensemanagerapi.enums.AccountType;

import java.util.Currency;

public class Account {
    private String id;
    private String organizationId;
    private String createdBy;
    private String name;
    private Long balance;
    private AccountType type;
    private Currency currency;

    public Account(
        String id,
        String organizationId,
        String createdBy,
        String name,
        Long balance,
        AccountType type,
        Currency currency
    ) {
        this.id = id;
        this.organizationId = organizationId;
        this.createdBy = createdBy;
        this.name = name;
        this.balance = balance;
        this.type = type;
        this.currency = currency;
    }
}
