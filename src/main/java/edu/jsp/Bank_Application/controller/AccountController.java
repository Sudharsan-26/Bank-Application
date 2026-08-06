package edu.jsp.Bank_Application.controller;

import java.util.List;

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

import edu.jsp.Bank_Application.service.AccountService;
import edu.jsp.Bank_Application.entity.Account;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user/{userId}/accounts")
public class AccountController {
	private AccountService service;
	
	@GetMapping("/{accountId}")
	public ResponseEntity<Account> getAccountById(@PathVariable long userId,@PathVariable long accountId) {
		return new ResponseEntity<Account>(service.getAccountById(userId, accountId),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Account> createAccount(@PathVariable long userId,@RequestBody Account account) {
		return new ResponseEntity<>(service.createAccount(userId, account),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{accountId}")
	public ResponseEntity<String> deleteAccount(@PathVariable long userId ,@PathVariable long accountId) {
		return new ResponseEntity<String>( service.DeleteAccount(userId, accountId),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccountByUserId(@PathVariable long userId){
		return new ResponseEntity<List<Account>>( service.getAllAccountByUserId(userId),HttpStatus.OK);
	}
	
	@PutMapping("/{accountId}")
	public ResponseEntity<Account> updateAccount(@PathVariable Long userId,@PathVariable Long accountId,@RequestBody Account account)
	{
		return ResponseEntity.ok( service.updateAccount(userId, accountId, account));
	}
}
