package com.bank.backend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "accounts")
public class Accounts {
    @Id
    private String accountId;

    private String username;

    private List<AccountDetails> accountDetails;
}


