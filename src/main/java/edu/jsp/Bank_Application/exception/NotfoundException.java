package edu.jsp.Bank_Application.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotfoundException extends RuntimeException{

	 private String resourceName;
	 private String fieldName;
	 private Long fieldId;
	 //user not found for id=1
	 
	 
	 @Override
	 public String getMessage()
	 {
		 return resourceName+" Not found for "+fieldName+" = "+fieldId;
	 }
}
