package com.theo.bankingapp.repository;
import  com.theo.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long>{

}
