package edu.jsp.Bank_Application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.jsp.Bank_Application.entity.Account;
import edu.jsp.Bank_Application.entity.Transaction;
import edu.jsp.Bank_Application.exception.NotfoundException;
import edu.jsp.Bank_Application.repository.AccountRepository;
import edu.jsp.Bank_Application.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
public class TransactionService {

	private TransactionRepository transactionRepo;
	
	private AccountRepository accountRepo;
	
	public Transaction addTransaction(Long userId,Long accountId, Transaction transaction)
	{
		Account a=accountRepo.findByIdAndUserId(accountId,userId).orElseThrow(()-> new NotfoundException("Account","AccountId",accountId));
		
		a.addTransaction(transaction);
		transaction.setDate(LocalDateTime.now());
		
		return transactionRepo.save(transaction);
		
	}
	
	//we want to work it !!!
	public Transaction deleteTransaction (Long userId,Long accountId,Long tId)
	{
		Account a=accountRepo.findByIdAndUserId(accountId,userId).orElseThrow(()-> new NotfoundException("Account", "AccountId", accountId));
		Transaction t=transactionRepo.findById(tId).orElseThrow(()-> new NotfoundException("Transaction","TransactionID", tId));
		
		a.getTransactions().remove(t);
		transactionRepo.delete(t);
		
		return t;
		
	}
	
	//-------------------
	public List<Transaction> getAllTransactionByUserid(Long userId,int pageNo,int pagesize)
	{
		Pageable page=PageRequest.of(pageNo, pagesize);
		
		return transactionRepo.getAllTransactionsbyUserId(userId,page );
	}
	
	
	public List<Transaction> getAllTransactionByUserIdAndDate(long userId, LocalDateTime st , LocalDateTime end){
		return transactionRepo.getAllTransactionsbyUserAndDate(userId, st, end);
	}
	
	public List<Transaction> getAllTransactionByUserIdAndAmount(long userId, double st , double end){
		return transactionRepo.getAllTransactionsbyUserAndAmount(userId, st, end);
	}
}
