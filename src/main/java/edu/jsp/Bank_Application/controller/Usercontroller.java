package edu.jsp.Bank_Application.controller;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.Bank_Application.DTO.UserDTO;
import edu.jsp.Bank_Application.entity.User;
import edu.jsp.Bank_Application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class Usercontroller {
	
	private UserService userservice;
	
	@PostMapping
	@Operation(summary = "Save the user",
	            description = "used to save the user details in db")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		return new ResponseEntity<User>(userservice.createUser(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id)
	{
		return new ResponseEntity<UserDTO>(userservice.getUserById(id),HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserById (@PathVariable Long id)
	{
		return new ResponseEntity<String>(userservice.deleteUserById(id),HttpStatus.OK);
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<List<User>> searchByUserName(@RequestParam String name)
	{
		return ResponseEntity.ok(userservice.searchByUserName(name));
	}
	
	//update is task to complete ourself
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User u)
	{
		return ResponseEntity.ok(userservice.UpdateUser(id, u));
	}
	

}
