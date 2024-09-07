package com.theo.bankingapp.service.impl;

import com.theo.bankingapp.dto.AccountDto;
import com.theo.bankingapp.entity.Account;
import com.theo.bankingapp.mapper.AccountMapper;
import com.theo.bankingapp.repository.AccountRepository;
import com.theo.bankingapp.service.AccountS;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountS {


	private final AccountRepository accountRepository;

    // Constructor Injection
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        // Map DTO to entity
        Account account = AccountMapper.mapToAccount(accountDto);

        // Save entity in DB
        Account savedAccount = accountRepository.save(account);

        // Map entity back to DTO
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    

    

	@Override
	public AccountDto getAccountById(Long id) {
		
    	Account account=accountRepository
    			.findById(id)
    			.orElseThrow(()-> new RuntimeException("Account does not exist"));
    	return AccountMapper.mapToAccountDto(account);
    	
    
    }
	
	@Override
	public AccountDto deposit(long id, double amount) {
		
		Account account=accountRepository
    			.findById(id)
    			.orElseThrow(()-> new RuntimeException("Account does not exist"));
    	
    	
    	double total= account.getBalance()+ amount;
    	
    	
    	account.setBalance(total);
    	
    	Account savedAccount=accountRepository.save(account);
    	return AccountMapper.mapToAccountDto(savedAccount);
    	
    	
	}

	@Override
	public AccountDto withdraw(long id, double amount) {
		Account account=accountRepository
    			.findById(id)
    			.orElseThrow(()-> new RuntimeException("Account does not exist"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficeint balance");
		}
		
		double total =account.getBalance()- amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
    	return AccountMapper.mapToAccountDto(savedAccount);
		
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {
		
		Account account=accountRepository
    			.findById(id)
    			.orElseThrow(()-> new RuntimeException("Account does not exist"));
    	accountRepository.deleteById(id);
    	
    	
		
		
	}
	
	
	
    
}
