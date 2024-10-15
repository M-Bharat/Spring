package com.example.sessiontimeoutdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

public class SessionStatusController {
	 @GetMapping("/sessionStatus")
	    public ResponseEntity<Void> checkSession() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        
	        if (authentication == null || !authentication.isAuthenticated()) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Send 401 if session is invalid
	        }
	        
	        return ResponseEntity.ok().build(); // Session is valid
	    }
}
