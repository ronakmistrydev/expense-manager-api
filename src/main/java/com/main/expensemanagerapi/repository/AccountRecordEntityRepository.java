package com.main.expensemanagerapi.repository;

import com.main.expensemanagerapi.domain.AccountRecord;
import com.main.expensemanagerapi.entity.AccountEntity;
import com.main.expensemanagerapi.entity.AccountRecordEntity;
import com.main.expensemanagerapi.entity.RootEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountRecordEntityRepository implements EntityRepository<AccountRecord> {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public AccountRecord getById(String id) {
        AccountRecordEntity byId = mongoTemplate.findById(id, AccountRecordEntity.class);
        assert byId != null;
        return AccountRecordEntity.toDomain(byId);
    }

    @Override
    public List<AccountRecord> findAll() {
        return null;
    }

    @Override
    public void save(AccountRecord accountRecord) {
        mongoTemplate.save(AccountRecordEntity.toEntity(accountRecord));
    }

    public List<AccountRecord> findByOrganization(final String organizationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(organizationId));
        List<String> accountIds = this.mongoTemplate.find(query, AccountEntity.class)
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
