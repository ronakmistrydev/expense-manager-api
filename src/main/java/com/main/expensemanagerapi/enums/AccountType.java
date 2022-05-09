package com.main.expensemanagerapi.enums;

public enum AccountType {
    DEBIT("DEBIT"),
    CREDIT_CARD("CREDIT_CARD"),
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
