package com.main.expensemanagerapi.entity;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.enums.RecordType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Document("accountRecord")
public class AccountRecordEntity extends RootEntity {

    private String categoryId;
    private BigDecimal amount;
    private Currency currency;
    private String note;
    private String payee;
    private String fromAccountId;
    private String toAccountId;
    private RecordType type;

    public AccountRecordEntity(
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

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public RecordType getType() {
        return type;
    }

    public static AccountRecordEntity toEntity(AccountRecord accountRecord) {
        return new AccountRecordEntity(
            accountRecord.getId(),
            accountRecord.getCategoryId(),
            accountRecord.getAmount(),
            accountRecord.getCurrency(),
            accountRecord.getCreatedAt(),
            accountRecord.getNote(),
            accountRecord.getPayee(),
            accountRecord.getFromAccountId(),
            accountRecord.getToAccountId(),
            accountRecord.getType()
        );
    }

    public static AccountRecord toDomain(AccountRecordEntity entity) {
        return new AccountRecord(
          entity.getId(),
          entity.getCategoryId(),
          entity.getAmount(),
          entity.getCurrency(),
          entity.getCreatedAt(),
          entity.getNote(),
          entity.getPayee(),
          entity.getFromAccountId(),
          entity.getToAccountId(),
          entity.getType()
        );
    }
}
