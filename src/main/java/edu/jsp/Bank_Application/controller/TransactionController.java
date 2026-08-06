package edu.jsp.Bank_Application.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.Bank_Application.entity.Account;
import edu.jsp.Bank_Application.entity.Transaction;
import edu.jsp.Bank_Application.repository.AccountRepository;
import edu.jsp.Bank_Application.service.TransactionService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class TransactionController {
	
	private TransactionService service;
	
//	//http://localhost:8080/user/1/accounts/1/transactions
//	@PostMapping("/user/{userId}/accounts/{accountId}/transactions")
//	public Transaction addTransaction(@PathVariable Long userId,@PathVariable Long accountId,@RequestBody Transaction transaction)
//	{
//		return service.addTransaction(userId, accountId, transaction);
//	}
//	
//	
//	//http://localhost:8080/user/1/transactions?pageNo=0&?pageSize=5
//	 @GetMapping("/user/{userId}/transactions")
//	public List<Transaction> getAllTransactionByUserid(@PathVariable Long userId,@RequestParam (required = false,defaultValue = "0") int pageNo, @RequestParam (required = false,defaultValue = "5") int pageSize)
//	{
//		return service.getAllTransactionByUserid(userId,pageNo,pageSize);
//	}
	 
	 
	    @PostMapping("/users/{userId}/transactions")
		public ResponseEntity<Transaction> addTransaction(@PathVariable long userId,@RequestBody Transaction transaction , @RequestParam long accountId) {
			return new ResponseEntity<Transaction>(service.addTransaction(userId, accountId, transaction), HttpStatus.CREATED);
		}
		
		@GetMapping("/users/{userId}/transactions")
		public ResponseEntity<List<Transaction>> getAllTransactionByUserId(@PathVariable long userId,@RequestParam(required = false,defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10",required = false) int pagesize ){
			return new ResponseEntity<List<Transaction>>(service.getAllTransactionByUserid(userId, pageNo, pagesize),HttpStatus.OK);
		}

		@GetMapping("/users/{userId}/transactions/date")
		public ResponseEntity<List<Transaction>> getAllTransactionByUserIdAndDate(@PathVariable long userId,@RequestParam LocalDateTime st , @RequestParam LocalDateTime end){
			return new ResponseEntity<List<Transaction>>(service.getAllTransactionByUserIdAndDate(userId, st, end), HttpStatus.OK);
		}
		
		@GetMapping("/users/{userId}/transactions/amount")
		public ResponseEntity<List<Transaction>> getAllTransactionByUserIdAndAmount(@PathVariable long userId,@RequestParam double st , @RequestParam double end){
			return new ResponseEntity<List<Transaction>>(service.getAllTransactionByUserIdAndAmount(userId, st, end), HttpStatus.OK);
		}

}
