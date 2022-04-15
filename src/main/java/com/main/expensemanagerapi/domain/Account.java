package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.dto.AccountTransactionDto;
import com.main.expensemanagerapi.enums.AccountType;

import java.util.Currency;
import java.util.Date;
import java.util.HashMap;

public class Account {
    private final String id;
    private final String organizationId;
    private Long balance;
    private final String createdBy;
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
        AccountType type
    ) {
        this.id = id;
        this.organizationId = organizationId;
        this.balance = balance;
        this.createdBy = createdBy;
        this.currency = currency;
        this.name = name;
        this.type = type;
        this.transactions = new HashMap<>();
    }

    public void credit(AccountTransactionDto dto) {
        new AccountTransaction(
            "account-transaction-credit"+new Date().getTime(),
            new Date(),
            dto
        );
    }

    public void debit(AccountTransactionDto dto) {
        new AccountTransaction(
            "account-transaction-debit"+new Date().getTime(),
            new Date(),
            dto
        );
    }

    public void updateTransaction(String transactionId, AccountTransactionDto dto) {
        AccountTransaction accountTransaction = this.transactions.get(transactionId);
        new AccountTransaction(
            accountTransaction.getId(),
            accountTransaction.getCreatedAt(),
            dto
        );
    }
}
