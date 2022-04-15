package com.main.expensemanagerapi.new_domain;

import com.main.expensemanagerapi.enums.AccountType;

import java.util.Currency;
import java.util.HashMap;

public class Account {
    private String id;
    private String organizationId;
    private Long balance;
    private String createdBy;
    private Currency currency;
    private String name;
    private AccountType type;
    private HashMap<String, AccountTransaction> transactions;

    public Account(
        String id,
        String organizationId,
        Long balance,
        String createdBy,
        Currency currency,
        String name,
        AccountType type,
        HashMap<String, AccountTransaction> transactions
    ) {
        this.id = id;
        this.organizationId = organizationId;
        this.balance = balance;
        this.createdBy = createdBy;
        this.currency = currency;
        this.name = name;
        this.type = type;
        this.transactions = transactions;
    }
}
