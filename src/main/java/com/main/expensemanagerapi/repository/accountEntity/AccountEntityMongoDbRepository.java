package com.main.expensemanagerapi.repository.accountEntity;

import com.main.expensemanagerapi.domain.account.Account;
import com.main.expensemanagerapi.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountEntityMongoDbRepository implements AccountEntityRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccountEntityMongoDbRepository(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Account getById(final String id) {
        AccountEntity account = this.mongoTemplate.findById(id, AccountEntity.class);
        assert account != null;
        return AccountEntity.toDomain(account);
    }

    @Override
    public void save(Account account) {
        mongoTemplate.save(AccountEntity.toEntity(account));
    }

    @Override
    public List<Account> findByOrganizationId(String organizationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("organizationId").is(organizationId));
        return this.mongoTemplate.find(query, AccountEntity.class)
                .stream()
                .map(AccountEntity::toDomain)
                .collect(Collectors.toList());
    }
}
