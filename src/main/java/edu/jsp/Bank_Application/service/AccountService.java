package edu.jsp.Bank_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.jsp.Bank_Application.exception.NotfoundException;
import edu.jsp.Bank_Application.repository.AccountRepository;
import edu.jsp.Bank_Application.repository.UserRepository;
import edu.jsp.Bank_Application.entity.Account;
import edu.jsp.Bank_Application.entity.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {
	private AccountRepository accRepo;
	private UserRepository userRepo;
	
	public Account getAccountById(long userId , long accountId) {
		return accRepo.findByIdAndUserId(accountId, userId)
				.orElseThrow(()->new NotfoundException("Account","AccountId",accountId));
	}
	
	public Account createAccount(long userId , Account account) {
		User u = userRepo.findById(userId).orElseThrow(()->new NotfoundException("User", "UserId", userId));
		u.addAccount(account);
		return accRepo.save(account);
	}
	
	public String DeleteAccount(long userId , long accountId) {
		User u =  userRepo.findById(userId).orElseThrow(()->new NotfoundException("User", "UserId", userId));
		Account a = accRepo.findById(accountId).orElseThrow(()->new NotfoundException("Account","AccountId",accountId));
		u.removeAccount(a);
		accRepo.delete(a);
		return "Data deleted";
	}
	
	public Account updateAccount (Long userId,Long accountId,Account account)
	{
		Optional<Account> o=accRepo.findByIdAndUserId(accountId,userId);
		
		Account a = o.orElseThrow(
			    () -> new NotfoundException("Account", "AccountId", accountId)
			);
		
		
			a.setAccountNo(account.getAccountNo());
			a.setBalance(account.getBalance());
		
			accRepo.save(a);
		
		return a;
	}
	
	public List<Account> getAllAccountByUserId(long UserId){
		return accRepo.findByUserId(UserId);
	}
}
