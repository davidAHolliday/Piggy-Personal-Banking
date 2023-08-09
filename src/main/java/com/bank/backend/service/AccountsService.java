package com.bank.backend.service;

import com.bank.backend.data.AccountDetailsRepository;
import com.bank.backend.data.AccountRepository;
import com.bank.backend.models.AccountDetails;
import com.bank.backend.models.Accounts;
import com.bank.backend.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountsService  {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountDetailsRepository accountDetailsRepository;


    public List<Accounts> getAllAccounts(){

        return accountRepository.findAll();
    }

    public Accounts save(Accounts accounts){
        return accountRepository.save(accounts);
    }

    public AccountDetails getAccountByAccountNumber(String userId,String loanNumber) {
        Accounts accts =  accountRepository.findByAccountId(userId);
        AccountDetails account =  accts.getAccountDetails().stream().filter(x-> x.getAccountNumber().equals(loanNumber)).findFirst().orElse(null);
        return account;
    }


    public List<AccountDetails> filterAccountByUserId(String userId) {
        Accounts accts =  accountRepository.findByUserId(userId);
        List<AccountDetails> account =  accts.getAccountDetails();
        return account;    }
}
