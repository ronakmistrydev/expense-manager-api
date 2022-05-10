package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.AccountTransaction;
import com.main.expensemanagerapi.entity.AccountEntity;
import com.main.expensemanagerapi.entity.AccountTransactionEntity;
import com.main.expensemanagerapi.entity.RootEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountTransactionEntityRepository implements EntityRepository<AccountTransaction> {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public AccountTransaction getById(String id) {
        AccountTransactionEntity byId = mongoTemplate.findById(id, AccountTransactionEntity.class);
        assert byId != null;
        return AccountTransactionEntity.toDomain(byId);
    }

    @Override
    public List<AccountTransaction> findAll() {
        return null;
    }

    @Override
    public void save(AccountTransaction accountTransaction) {
        mongoTemplate.save(AccountTransactionEntity.toEntity(accountTransaction));
    }

    public List<AccountTransaction> findByOrganization(final String organizationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(organizationId));
        List<String> accountIds = this.mongoTemplate.find(query, AccountEntity.class)
                .stream().map(RootEntity::getId)
                .collect(Collectors.toList());

        Query accountTransactionEntityQuery = new Query();
        accountTransactionEntityQuery.addCriteria(Criteria.where("fromAccountId").in(accountIds));
        return this.mongoTemplate.find(accountTransactionEntityQuery, AccountTransactionEntity.class)
                .stream()
                .map(AccountTransactionEntity::toDomain)
                .collect(Collectors.toList());
    }
}
