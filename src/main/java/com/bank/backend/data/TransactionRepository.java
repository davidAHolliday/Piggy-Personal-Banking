package com.bank.backend.data;

import com.bank.backend.models.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transactions, String> {
}
