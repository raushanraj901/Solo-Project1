package com.project.globalException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.project.customException.AdminExecption;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(AdminExecption.class)
	public ResponseEntity<MyErrorDetails> myCustomerHandller(AdminExecption bnf,WebRequest wr){
		
		MyErrorDetails e = new MyErrorDetails();
		e.setTimeStamp(LocalDateTime.now());
		e.setMessage(bnf.getMessage());
		e.setDescription(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(e,HttpStatus.BAD_REQUEST);
	
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> allinoneException(Exception bnf,WebRequest wr){
		MyErrorDetails e = new MyErrorDetails();
		e.setTimeStamp(LocalDateTime.now());
		e.setMessage(bnf.getMessage());
		e.setDescription(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(e,HttpStatus.BAD_REQUEST);
	}

}
