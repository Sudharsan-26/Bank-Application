package edu.jsp.Bank_Application.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.jsp.Bank_Application.entity.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

	@Query(value = "select t from Transaction t where t.account.user.id= ?1 order by date")
	List<Transaction> getAllTransactionsbyUserId(Long userId,Pageable pageable);
	
	@Query(value = "select t from Transaction t where t.account.user.id = ?1 and t.date between ?2 and ?3")
	List<Transaction> getAllTransactionsbyUserAndDate(Long userId, LocalDateTime st , LocalDateTime end);

    @Query(value = "select t from Transaction t where t.account.user.id = ?1 and t.amount between ?2 and ?3")
    List<Transaction> getAllTransactionsbyUserAndAmount(Long userId, double st , double end);
	
}
