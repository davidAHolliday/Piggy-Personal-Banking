package com.bank.backend.models;

import lombok.Data;

import java.util.List;


@Data
public class AccountSummaryDAO {

    private double balance;
    private AccountDetails accountDetails;
    private List<Transactions> transactions;
}
