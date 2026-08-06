package edu.jsp.Bank_Application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jsp.Bank_Application.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	
	Optional<Account> findByIdAndUserId(long accountId , long userId);
	
	List<Account> findByUserId(long userId);
}
