package com.bank.backend.service;

import com.bank.backend.data.TransactionRepository;
import com.bank.backend.models.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transactions> getAllTransactions(){
        return transactionRepository.findAll();

    }

    public Transactions save(Transactions transactions){
        return transactionRepository.save(transactions);

    }



}
