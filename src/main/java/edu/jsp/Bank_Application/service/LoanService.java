package edu.jsp.Bank_Application.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.jsp.Bank_Application.entity.Loan;
import edu.jsp.Bank_Application.entity.User;
import edu.jsp.Bank_Application.exception.NotfoundException;
import edu.jsp.Bank_Application.repository.LoanRepository;
import edu.jsp.Bank_Application.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanService {
	
	
	private final UserRepository userRepo;//we use constructor injection instead of Autowired because we need to use the final keyword which is need not to be intialised again
	private LoanRepository loanRepo;
	
	@Transactional
	public Loan applyLoan(Long userId , Loan loan) {
		User u = userRepo.findById(userId).orElseThrow(()->new NotfoundException("User","Id",userId));
		u.addLoan(loan);
		
		return loanRepo.save(loan);
	}
	
	public Loan getLoanById(long userId,long loanId) {
		return loanRepo.findByUserIdAndId(userId,loanId).orElseThrow(()->new NotfoundException("Loan","Id",loanId));
	}
	
	
	@Transactional
	public String deleteLoan(long userId , Long loanId) {
		
//		User u = userRepo.findById(userId).orElseThrow(()->new NotfoundException("User","Id",userId));
//		Loan l = loanRepo.findById(loanId).orElseThrow(()->new NotfoundException("Loan","Id",loanId));
//		u.removeLoan(l); ---> instead of using this below one is preferred.
		
		Loan l = loanRepo.findByUserIdAndId(userId,loanId).orElseThrow(()->new NotfoundException("Loan","Id",loanId));
		loanRepo.delete(l);
		
		return "Data Deleted";
	}
	
	public List<Loan> getLoanByUserId(long userId){
		return loanRepo.getLoanByUserId(userId);
	}
	
	
	public String repayLoan(long userId,long loanId,double amount)
	{
		Loan l=loanRepo.findByUserIdAndId(userId,loanId).orElseThrow(()->new NotfoundException("Loan","Id",loanId));
		
		l.setBalance(l.getBalance()-amount);
		
		if (l.getBalance()<=0)
		{
			l.setStatus("repaid");
		}
		
		loanRepo.save(l);
		
		return "Loan amount paid";
		
	}
	
	public String getLoanStatus(long userId,long loanId)
	{
		Loan l=loanRepo.findByUserIdAndId(userId,loanId).orElseThrow(()-> new NotfoundException("Loan","Id",loanId));
		
		return l.getStatus();
	}
	
}