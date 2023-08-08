package com.bank.backend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "accountDetails") // Specify the collection name
public class AccountDetails {


    private String accountNumber;

    private Date dateAccountOpen;
    private double loanAmount;
    private double interestRate;
    private int loanTerm;
}
