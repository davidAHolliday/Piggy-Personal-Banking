package com.bank.backend.data;

import com.bank.backend.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Users,String> {
    Users getUsersByUserId(String userId);



}
