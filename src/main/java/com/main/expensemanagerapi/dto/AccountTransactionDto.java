package com.main.expensemanagerapi.dto;

import com.main.expensemanagerapi.enums.TransactionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

public class AccountTransactionDto implements Serializable {
    private String categoryId;
    private BigDecimal amount;
    private Currency currency;
    private String note;
    private String payee;
    private String toAccountId;
    private TransactionType type;

    public String getCategoryId() {
        return categoryId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getNote() {
        return note;
    }

    public String getPayee() {
        return payee;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public TransactionType getType() {
        return type;
    }
}
