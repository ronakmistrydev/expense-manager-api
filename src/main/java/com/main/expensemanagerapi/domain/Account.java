package com.main.expensemanagerapi.domain;

public class Account {
    private String id;
    private Long balance;
    private String name;
    private String currency;

    private String organizationId;

    public String getId() {
        return id;
    }

    public Long getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public Account create(String id, Long balance, String name, String currency, String organizationId) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.currency = currency;
        this.organizationId = organizationId;
        return this;
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
