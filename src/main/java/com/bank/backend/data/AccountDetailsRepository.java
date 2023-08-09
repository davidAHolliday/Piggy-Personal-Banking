package com.bank.backend.data;

import com.bank.backend.models.AccountDetails;
import com.bank.backend.models.Accounts;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountDetailsRepository  extends MongoRepository<AccountDetails,String> {


}
