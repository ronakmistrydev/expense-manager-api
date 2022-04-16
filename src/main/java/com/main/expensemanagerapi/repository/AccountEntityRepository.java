package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.Account;
import com.main.expensemanagerapi.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountEntityRepository implements EntityRepository<Account> {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Account getById(String id) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public void save(Account account) {
        mongoTemplate.save(AccountEntity.toEntity(account));
    }
}
