package com.theo.bankingapp.service;

import com.theo.bankingapp.dto.AccountDto;
import java.util.*;

public interface AccountS {
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(long id, double amount);
	
	AccountDto withdraw(long id ,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);
}
