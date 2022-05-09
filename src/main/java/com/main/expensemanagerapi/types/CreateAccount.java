package com.main.expensemanagerapi.types;

import com.main.expensemanagerapi.enums.AccountType;

import java.math.BigDecimal;

public class CreateAccount {
    private final String name;
    private final BigDecimal balance;
    private final AccountType type;
    private final String currency;
    private final String organizationId;

    public CreateAccount(String name, BigDecimal balance, AccountType type, String currency, String organizationId) {
        this.name = name;
        this.balance = balance;
        this.type = type;
        this.currency = currency;
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountType getType() {
        return type;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOrganizationId() {
        return organizationId;
    }
}
