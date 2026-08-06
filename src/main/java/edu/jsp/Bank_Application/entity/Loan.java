package edu.jsp.Bank_Application.entity;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double amount;
	private LocalDateTime applyDate;
	private LocalDateTime approvalDate;
	private LocalDateTime disbursementDate;//means the date when the loan amount is given to the user
	private double balance;
	private String status;
//	could be "Applied" , "Approved" , "Denied"
	
	
	@ManyToOne
	@JoinColumn
	@JsonBackReference("user-loan")//it is used to ignore the recurssive call of this mapping.
	private User user;
}