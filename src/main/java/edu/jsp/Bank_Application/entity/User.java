package edu.jsp.Bank_Application.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 6 , max=20 , message = "Name should be within the length")
	private String name;
	
	@Email(message = "Enter proper email")
	@Column(unique = true)
	private String email;
	
	@Size(min = 8 , message = "Minimum Password length should be 8")
	private String password;
	
	
	
	// --------------------------------------------------------------------
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
	@JsonManagedReference("user-loan")
	private Set<Loan> loans;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
	@JsonManagedReference("user-account")
	private Set<Account> accounts;
	
	// --------------------------------------------------------------------
	
	public void addLoan(Loan l) {
		loans.add(l);
		l.setUser(this);
	}
	
	public void removeLoan(Loan l) {
		loans.remove(l);
		l.setUser(null);
	}
	
	public void addAccount(Account a) {
		accounts.add(a);
		a.setUser(this);
	}
	
	public void removeAccount(Account a) {
		accounts.remove(a);
		a.setUser(null);
	}
}
