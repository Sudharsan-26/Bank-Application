package edu.jsp.Bank_Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.jsp.Bank_Application.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{

	List<User> findByName(String name);
	
	
}
