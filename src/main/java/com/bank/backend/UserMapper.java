package com.bank.backend;

import com.bank.backend.models.Users;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserMapper {
    public static List<UserResponse> mapUserToUserResponseList(List<Users> usersList) {
        Function<Users, UserResponse> userToUserResponse = user -> new UserResponse(user.getUsername(), user.getEmail(), "");

        return usersList.stream()
                .map(userToUserResponse)
                .collect(Collectors.toList());
    }

    public static UserResponse mapUserToUserResponse(Users users) {
        if(null !=users) {
            Function<Users, UserResponse> userToUserResponse = user -> new UserResponse(user.getUsername(), user.getEmail(), "");
            UserResponse res = userToUserResponse.apply(users);
            return res;
        }
        return new UserResponse("","","User Not Found");


    }
}
