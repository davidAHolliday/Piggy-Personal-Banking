package com.bank.backend;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserResponse {

    String userid;
    String userName;

    String email;

    String error;

}
