package edu.jsp.Bank_Application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.jsp.Bank_Application.DTO.UserDTO;
import edu.jsp.Bank_Application.entity.User;
import edu.jsp.Bank_Application.exception.NotfoundException;
import edu.jsp.Bank_Application.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepo;
	
	public User createUser(User user)
	{
		return userRepo.save(user);
	}
	
	public UserDTO getUserById(Long id)
	{
		User u= userRepo.findById(id).orElseThrow(()->new NotfoundException("User","Id",id));
		UserDTO udto=new UserDTO();
		udto.setName(u.getName());
		udto.setEmail(u.getEmail());
		udto.setPassword(u.getPassword());
		
		return udto;
	}
	
	public String deleteUserById (Long id)
	{
		User u=userRepo.findById(id).orElseThrow(()->new NotfoundException("User"," Id",id));
		
		userRepo.delete(u);
		
		return "Data Deleted";
	}
	
	public List<User> searchByUserName(String name)
	{
		return userRepo.findByName(name);
	}
	
	public User UpdateUser(Long id,User u)
	{
		User o=userRepo.findById(id).orElse(null);
		
		if (o!=null)
		{
			o.setName(u.getName());
			o.setEmail(u.getEmail());
			o.setPassword(u.getPassword());
			userRepo.save(o);
		}
		return o;
	}
}
