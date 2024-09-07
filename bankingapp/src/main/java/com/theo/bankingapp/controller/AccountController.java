package com.theo.bankingapp.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theo.bankingapp.dto.AccountDto;
import com.theo.bankingapp.service.AccountS;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	
	
	private AccountS accountService;
	
	public AccountController(AccountS accountService) {
		this.accountService=accountService;
		
	}
	
	//Add account REST api
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
	    return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//get account rest api
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountbyId(@PathVariable Long id){
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
		
	}
	
	//deposit rest api
	
	@PutMapping("/{id}/deposit")
	
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
		
		
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	// withdraw rest api
	@PutMapping("/{id}/withdraw")
	
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.withdraw(id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	// get all accounts api
	
	@GetMapping("/getall")
	public ResponseEntity<List<AccountDto>> getallAccounts(){
		List<AccountDto> accounts=accountService.getAllAccounts();
		
		return ResponseEntity.ok(accounts);
	}
	
	//delete an account by id
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id){
		accountService.deleteAccount(id);
		
		return ResponseEntity.ok("Account is deleted successfully");
	}
	

	
	
	
	
	
	
	
	
}
