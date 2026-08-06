package edu.jsp.Bank_Application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.Bank_Application.entity.Loan;
import edu.jsp.Bank_Application.service.LoanService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LoanController {
	
	private LoanService loanservice;
	
	@PostMapping("users/{userId}/loans")
	public ResponseEntity<Loan> applyLoan(@PathVariable Long userId,@RequestBody Loan loan)
	{
		return new  ResponseEntity<Loan>(loanservice.applyLoan(userId, loan),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users/{userId}/loans/{loanId}")
	public ResponseEntity<Loan> getLoanById(@PathVariable long userId,@PathVariable long loanId)
	{
		return ResponseEntity.ok(loanservice.getLoanById(userId, loanId));
	}
	
	@DeleteMapping("/users/{userId}/loans/{loanId}")
	public ResponseEntity<String> deleteLoan(@PathVariable long userId,@PathVariable Long loanId)
	{
		return new ResponseEntity<String>(loanservice.deleteLoan(userId, loanId),HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}/loans")
	public ResponseEntity<List<Loan>> getLoanByuserId(@PathVariable long userId)
	{
		return new ResponseEntity<List<Loan>>(loanservice.getLoanByUserId(userId),HttpStatus.OK);
	}
	
	@PutMapping("/users/{userId}/loans/{loanId}/repay/{amount}")
	public ResponseEntity<String> repayLoan(@PathVariable long userId,@PathVariable long loanId,@PathVariable double amount)
	{
		return new ResponseEntity<String>(loanservice.repayLoan(userId, loanId, amount),HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}/loans/{loanId}/status")
	public ResponseEntity<String> getLoanStatus(@PathVariable long userId,@PathVariable long loanId)
	{
		return new ResponseEntity<String>(loanservice.getLoanStatus(userId, loanId),HttpStatus.OK);
	}

}
