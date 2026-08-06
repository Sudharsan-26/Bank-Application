package edu.jsp.Bank_Application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.jsp.Bank_Application.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

	@Query(value = "select l from Loan l where l.user.id = ?1")
	List<Loan> getLoanByUserId(long userId);
	
	public Optional<Loan> findByUserIdAndId(long userId,long loanId);
}
