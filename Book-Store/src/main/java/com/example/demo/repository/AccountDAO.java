package com.example.demo.repository;

import com.example.demo.entites.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends CrudRepository<Account, String> {

    Account findByUsername(@Param("username") String username);
}
