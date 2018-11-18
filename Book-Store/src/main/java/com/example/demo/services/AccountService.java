package com.example.demo.services;

import com.example.demo.entites.Account;

public interface AccountService  {

    public void create(Account account);

    public Account login(String username , String password);

    public Account find(String username);
}
