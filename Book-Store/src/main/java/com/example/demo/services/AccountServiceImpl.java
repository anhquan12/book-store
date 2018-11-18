package com.example.demo.services;

import com.example.demo.entites.Account;
import com.example.demo.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void create(Account account) {
        accountDAO.save(account);
    }

    @Override
    public Account login(String username, String password) {
        Account account = this.find(username);
        if(account != null) {
                return account;
        }
        return null;
    }

    @Override
    public Account find(String username) {
        return accountDAO.findByUsername(username);
    }
}
