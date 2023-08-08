package com.bank.backend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Document(collection = "transactions")
public class Transactions {
    @Id
    private String transactionId;
    private String accountId;
    private String transactionType;
    private double transactionAmount;
    private Date transactionDate;

}
