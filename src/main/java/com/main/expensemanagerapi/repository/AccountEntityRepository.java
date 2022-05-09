package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Account> findByOrganizationId(final String organizationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(organizationId));
        return this.mongoTemplate.find(query, AccountEntity.class)
                .stream()
                .map(AccountEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Account account) {
        mongoTemplate.save(AccountEntity.toEntity(account));
    }
}
