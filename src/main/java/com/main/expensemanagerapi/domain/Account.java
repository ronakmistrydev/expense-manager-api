package com.main.expensemanagerapi.domain;

public class Account {
    private String id;
    private Long balance;
    private String name;
    private String currency;
    private String organizationId;

    public void create(String id, Long balance, String name, String currency, String organizationId) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.currency = currency;
        this.organizationId = organizationId;
    }

    public void credit(Long amount) {
        //TODO:: validation
        this.balance -= amount;
    }

    public void debit(Long amount) {
        //TODO:: validation
        this.balance += amount;
    }
}
