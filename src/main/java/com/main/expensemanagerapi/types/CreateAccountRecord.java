package com.main.expensemanagerapi.types;

import com.main.expensemanagerapi.enums.RecordType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAccountRecord {
    private final String categoryId;
    private final BigDecimal amount;
    private final String currency;
    private final String note;
    private final String payee;
    private final String fromAccountId;
    private final String toAccountId;
    private final RecordType type;

    public CreateAccountRecord(
            String categoryId,
            BigDecimal amount,
            String currency,
            String note,
            String payee,
            String fromAccountId,
            String toAccountId,
            RecordType type
    ) {
        this.categoryId = categoryId;
        this.amount = amount;
        this.currency = currency;
        this.note = note;
        this.payee = payee;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
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
        return LocalDate.now();
    }

    public LocalTime getTime() {
        return LocalTime.now();
    }
}
