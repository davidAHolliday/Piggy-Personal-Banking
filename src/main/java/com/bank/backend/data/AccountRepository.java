package com.bank.backend.data;

import com.bank.backend.models.Accounts;
import com.bank.backend.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Accounts,String> {
    Users getByAccountId(String userId);

    Accounts findByAccountId(String accountNumber);
}
