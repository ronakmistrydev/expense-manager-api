package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.enums.RecordType;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class AccountRecord {

    private final String id;
    private final String categoryId;
    private final BigDecimal amount;
    private final Currency currency;
    private final Date createdAt;
    private final String note;
    private final String payee;
    private final String fromAccountId;
    private final String toAccountId;
    private final RecordType type;

    public AccountRecord(
        String id,
        String categoryId,
        BigDecimal amount,
        Currency currency,
        Date createdAt,
        String note,
        String payee,
        String fromAccountId,
        String toAccountId,
        RecordType type
    ) {
        this.id = id;
        this.categoryId = categoryId;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.note = note;
        this.payee = payee;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getNote() {
        return note;
    }

    public String getPayee() {
        return payee;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public RecordType getType() {
        return type;
    }
}
