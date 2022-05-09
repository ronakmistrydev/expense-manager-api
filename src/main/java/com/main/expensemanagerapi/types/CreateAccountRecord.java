package com.main.expensemanagerapi.types;

import com.main.expensemanagerapi.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

public class CreateAccountRecord {
    private final String categoryId;
    private final BigDecimal amount;
    private final String currency;
    private final String note;
    private final String payee;
    private final String fromAccountId;
    private final String toAccountId;
    private final TransactionType type;

    private final Date date;
    private final Date time;

    private final String organizationId;

    public CreateAccountRecord(String categoryId, BigDecimal amount, String currency, String note, String payee, String fromAccountId, String toAccountId, TransactionType type, Date date, Date time, String organizationId) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.currency = currency;
        this.note = note;
        this.payee = payee;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.date = date;
        this.time = time;
        this.organizationId = organizationId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
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

    public TransactionType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }

    public String getOrganizationId() {
        return organizationId;
    }
}
