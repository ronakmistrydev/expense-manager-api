package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.dto.AccountTransactionDto;
import com.main.expensemanagerapi.enums.AccountType;

import java.util.Currency;
import java.util.Date;
import java.util.HashMap;

public class Account {
    private final String id;
    private final String organizationId;
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
        this.createdBy = createdBy;
        this.currency = currency;
        this.name = name;
        this.type = type;
        this.transactions = new HashMap<>();
    }

    public void addTransaction(AccountTransactionDto dto) {
        String accountTransactionId = "account-transaction-"+new Date().getTime();
        this.transactions.put(
            accountTransactionId,
            new AccountTransaction(
                "account-transaction-"+new Date().getTime(),
                new Date(),
                dto
            )
        );
    }

    public void updateTransaction(String transactionId, AccountTransactionDto dto) {
        //TODO:: validation check
        AccountTransaction accountTransaction = this.transactions.get(transactionId);
        this.transactions.put(
            transactionId,
            new AccountTransaction(
                    accountTransaction.getId(),
                    accountTransaction.getCreatedAt(),
                    dto
            )
        );
    }
}
