package com.main.expensemanagerapi.types;

import com.main.expensemanagerapi.enums.RecordType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateAccountRecord {

    private final String id;
    private final String categoryId;
    private final BigDecimal amount;
    private final String currency;
    private final String note;
    private final String payee;
    private final String fromAccountId;
    private final String toAccountId;
    private final RecordType type;
    private final LocalDate date;
    private final LocalTime time;
    private final String organizationId;

    public UpdateAccountRecord(String id, String categoryId, BigDecimal amount, String currency, String note, String payee, String fromAccountId, String toAccountId, RecordType type, LocalDate date, LocalTime time, String organizationId) {
        this.id = id;
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

    public String getId() {
        return id;
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

    public RecordType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getOrganizationId() {
        return organizationId;
    }
}
