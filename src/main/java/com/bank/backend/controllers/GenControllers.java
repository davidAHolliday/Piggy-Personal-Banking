package com.bank.backend.controllers;

import com.bank.backend.UserMapper;
import com.bank.backend.UserResponse;
import com.bank.backend.models.*;
import com.bank.backend.service.AccountsService;
import com.bank.backend.service.TransactionsService;
import com.bank.backend.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/controller/")
public class GenControllers {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id ){
        Users users =  usersService.getUsersByUserId(id);
        Function<Users, UserResponse> mapUserToUserResponse = UserMapper::mapUserToUserResponse;
        UserResponse res = mapUserToUserResponse.apply(users);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("users/")
    public List<UserResponse> getAllUsers(){

        //This function can accept List of users and apply the reference of size and return a interger
        Function<List<Users>,Integer> listLength = List::size;
        List<Users> lstUser = usersService.getAllUsers();
        Function<List<Users>,List<UserResponse>> mapToUserFunction =UserMapper::mapUserToUserResponseList;
        int length = listLength.apply(lstUser);
        System.out.println(length);
        List<UserResponse> userResponseList = mapToUserFunction.apply(lstUser);
        lstUser.forEach((user)->System.out.println(user.toString()));

        return userResponseList;
    }


    @PostMapping("users/")
    public Users saveNewUsers(@RequestBody Users users){
        return usersService.save(users);
    }


    //accounts
    @PostMapping("accounts/")
    public Accounts saveNewAccounts(@RequestBody Accounts accounts) {
        List<AccountDetails> requestBody = accounts.getAccountDetails();
        System.out.println(requestBody);
        //If accountNumber is new, create first draw

        requestBody.forEach(accountDetail -> {
            if(accounts.getAccountId()==null){
                System.out.println("Adding Initial Draw of " +accountDetail.getLoanAmount());
                Transactions trans = new Transactions();
                trans.setAccountId(accountDetail.getAccountNumber());
                trans.setTransactionDate(new Date());
                trans.setTransactionAmount(accountDetail.getLoanAmount());
                trans.setTransactionType("draw");
                transactionsService.save(trans);

            } else if(accountsService.getAccountByAccountNumber(accounts.getAccountId(),accountDetail.getAccountNumber())== null) {
                System.out.println("Adding Initial Draw of " +accountDetail.getLoanAmount());
                Transactions trans = new Transactions();
                trans.setAccountId(accountDetail.getAccountNumber());
                trans.setTransactionDate(new Date());
                trans.setTransactionAmount(accountDetail.getLoanAmount());
                trans.setTransactionType("draw");
               transactionsService.save(trans);            }
            else{
                System.out.println("Account is already Created");
            }
        });

        return accountsService.save(accounts);
    }

    @GetMapping("accounts/")
    public List<Accounts> getAllAccounts(){

        List<Accounts> lstAcct = accountsService.getAllAccounts();

        return lstAcct;
    }

    @GetMapping("accounts/{userId}/{loanNumber}")
    public AccountDetails getAccountDetailsByNumber(@PathVariable String userId, @PathVariable String loanNumber){

        AccountDetails lstAcct = accountsService.getAccountByAccountNumber(userId,loanNumber);
        return lstAcct;
    }

    @GetMapping("accounts/summary/{userId}/{loanNumber}")
    public AccountSummaryDAO getAccountSummaryByNumber(@PathVariable String userId, @PathVariable String loanNumber) {

        AccountDetails lstAcct = accountsService.getAccountByAccountNumber(userId, loanNumber);
        AccountSummaryDAO summary = new AccountSummaryDAO();
        summary.setAccountDetails(lstAcct);

        List<Transactions> filteredTransactions = transactionsService.getAllTransactions()
                .stream()
                .filter(x -> x.getAccountId().equals(loanNumber))
                .collect(Collectors.toList());

        double totalTransactionAmount = filteredTransactions.stream()
                .mapToDouble(transaction -> {
                    if (transaction.getTransactionType().equals("payment")) {
                        return transaction.getTransactionAmount();
                    } else if (transaction.getTransactionType().equals("draw")) {
                        return -transaction.getTransactionAmount();
                    } else if (transaction.getTransactionType().equals("interest")) {
                        return 0.0;
                    } else {
                        return 0.0; // Handle other types of transactions if needed
                    }
                })
                .sum();

        summary.setBalance(totalTransactionAmount);
        summary.setTransactions(filteredTransactions); // Set the filtered transactions list

        return summary;
    }
//    @GetMapping("accounts/user/{userId}")
//    public List<Accounts> getAccountsByUserId(@PathVariable String userId){
//
//        List<Accounts> lstAcct = accountsService.getAllAccounts();
//        List<Accounts> filteredAccts = lstAcct.stream().filter((x)->{
//            return x.getUserId().equals(userId);
//        }).collect(Collectors.toList());
//
//        return filteredAccts;
//    }

//    @GetMapping("accounts/{acctNum}")
//    public List<Accounts> getAccountsByAcctNum(@PathVariable String acctNum){
//
//        List<Accounts> lstAcct = accountsService.getAllAccounts();
//        List<Accounts> filteredAccts = lstAcct.stream().filter((x)->{
//            if (x.getAccountDetails().getLoanAccountNumber() !=null){
//                return x.getAccountDetails().getLoanAccountNumber().equals(acctNum);
//            }
//
//            return false;
//        }).collect(Collectors.toList());
//
//        return  filteredAccts;
//    }

    @PostMapping("transactions/")
    public Transactions saveNewTransaction(@RequestBody Transactions transactions){
        return transactionsService.save(transactions);
    }

    @GetMapping("transactions/")
    public List<Transactions> getAllTransactions(){
        return transactionsService.getAllTransactions();
    }



}

