package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.dto.AccountRecordDto;
import com.main.expensemanagerapi.enums.RecordType;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class AccountRecord {

    private String id;
    private String categoryId;
    private BigDecimal amount;
    private Currency currency;
    private Date createdAt;
    private String note;
    private String payee;
    private String fromAccountId;
    private String toAccountId;
    private RecordType type;

    //TODO:: add record users

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

    public AccountRecord(
        String id,
        Date createdAt,
        AccountRecordDto dto
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
