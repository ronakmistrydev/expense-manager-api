package com.main.expensemanagerapi.domain;

public enum AccountType {
    CREDIT_CARD("DEBIT"),
    GENERAL("GENERAL"),
    CASH("CASH"),
    CURRENT("CURRENT"),
    ACCOUNT_WITH_OVERDRAFT("ACCOUNT_WITH_OVERDRAFT"),
    SAVINGS("SAVINGS"),
    BONUS("BONUS"),
    INSURANCE("INSURANCE"),
    INVESTMENT("INVESTMENT"),
    LOAN("LOAN"),
    MORTGAGE("MORTGAGE");

    private String name;

    AccountType(String name) {
        this.name = name;
    }
}
