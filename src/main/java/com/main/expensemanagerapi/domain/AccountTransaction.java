package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.dto.AccountTransactionDto;
import com.main.expensemanagerapi.enums.TransactionType;

import java.util.Currency;
import java.util.Date;

public class AccountTransaction {

    private String id;
    private String categoryId;
    private Long amount;
    private Currency currency;
    private Date createdAt;
    private String note;
    private String payee;
    private String toAccountId;
    private TransactionType type;

    public AccountTransaction(
        String id,
        String categoryId,
        Long amount,
        Currency currency,
        Date createdAt,
        String note,
        String payee,
        String toAccountId,
        TransactionType type
    ) {
        this.id = id;
        this.categoryId = categoryId;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.note = note;
        this.payee = payee;
        this.toAccountId = toAccountId;
        this.type = type;
    }

    public AccountTransaction(
        String id,
        Date createdAt,
        AccountTransactionDto dto
    ) {
        this.id = id;
        this.categoryId = dto.getCategoryId();
        this.amount = dto.getAmount();
        this.currency = dto.getCurrency();
        this.createdAt = createdAt;
        this.note = dto.getNote();
        this.payee = dto.getPayee();
        this.toAccountId = dto.getToAccountId();
        this.type = dto.getType();
    }

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
