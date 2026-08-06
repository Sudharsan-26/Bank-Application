package edu.jsp.Bank_Application.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long accountNo;
	private double balance;
	
	@ManyToOne
	@JoinColumn
	@JsonBackReference("user-account")
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
	private Set<Transaction> transactions;

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
		transaction.setAccount(this);
		
		
	}
}
