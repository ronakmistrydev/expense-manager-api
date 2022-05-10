package com.main.expensemanagerapi.repository.accountRecordEntity;


import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.entity.AccountEntity;
import com.main.expensemanagerapi.entity.AccountRecordEntity;
import com.main.expensemanagerapi.entity.RootEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountRecordEntityMongoDbRepository implements AccountRecordEntityRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccountRecordEntityMongoDbRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public AccountRecord getById(String id) {
        AccountRecordEntity byId = mongoTemplate.findById(id, AccountRecordEntity.class);
        assert byId != null;
        return AccountRecordEntity.toDomain(byId);
    }

    @Override
    public void save(AccountRecord accountRecord) {
        mongoTemplate.save(AccountRecordEntity.toEntity(accountRecord));
    }

    @Override
    public List<AccountRecord> findByOrganizationId(String organizationId) {
        Query accountEntityQuery = new Query();
        accountEntityQuery.addCriteria(Criteria.where("organizationId").is(organizationId));
        List<String> accountIds = this.mongoTemplate.find(accountEntityQuery, AccountEntity.class)
                .stream().map(RootEntity::getId)
                .collect(Collectors.toList());

        Query accountRecordEntityQuery = new Query();
        accountRecordEntityQuery.addCriteria(Criteria.where("fromAccountId").in(accountIds));
        return this.mongoTemplate.find(accountRecordEntityQuery, AccountRecordEntity.class)
                .stream()
                .map(AccountRecordEntity::toDomain)
                .collect(Collectors.toList());
    }
}
