package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.AccountTransaction;
import com.main.expensemanagerapi.enums.TransactionType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Document("accountTransaction")
public class AccountTransactionEntity extends RootEntity {

    private String categoryId;
    private BigDecimal amount;
    private Currency currency;
    private String note;
    private String payee;
    private String fromAccountId;
    private String toAccountId;
    private TransactionType type;

    public AccountTransactionEntity(
        String id,
        String categoryId,
        BigDecimal amount,
        Currency currency,
        Date createdAt,
        String note,
        String payee,
        String fromAccountId,
        String toAccountId,
        TransactionType type
    ) {
        super(id);
        this.categoryId = categoryId;
        this.amount = amount;
        this.currency = currency;
        this.note = note;
        this.payee = payee;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = new Date();
    }

    public static AccountTransactionEntity toEntity(AccountTransaction accountTransaction) {
        return new AccountTransactionEntity(
            accountTransaction.getId(),
            accountTransaction.getCategoryId(),
            accountTransaction.getAmount(),
            accountTransaction.getCurrency(),
            accountTransaction.getCreatedAt(),
            accountTransaction.getNote(),
            accountTransaction.getPayee(),
            accountTransaction.getFromAccountId(),
            accountTransaction.getToAccountId(),
            accountTransaction.getType()
        );
    }
}
