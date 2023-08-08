package com.bank.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@AllArgsConstructor
@Data
@Document(collection = "users")
public class Users {
    @Id
    private String userId;
    private String username;
    private String email;
    private String password;


}
