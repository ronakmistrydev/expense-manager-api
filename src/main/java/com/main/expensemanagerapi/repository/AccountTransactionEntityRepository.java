package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.AccountTransaction;
import com.main.expensemanagerapi.entity.AccountTransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountTransactionEntityRepository implements EntityRepository<AccountTransaction> {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public AccountTransaction getById(String id) {
        return null;
    }

    @Override
    public List<AccountTransaction> findAll() {
        return null;
    }

    @Override
    public void save(AccountTransaction accountTransaction) {
        mongoTemplate.save(AccountTransactionEntity.toEntity(accountTransaction));
    }
}
