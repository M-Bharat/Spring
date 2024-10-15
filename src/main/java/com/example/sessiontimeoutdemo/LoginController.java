package com.example.sessiontimeoutdemo;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
	 @GetMapping("/login")
	    public String login() {
	        return "login";
	    }

	    @GetMapping("/dashboard")
	    public String dashboard() {
	        return "dashboard";
	    }

	    @GetMapping("/sessionTimeoutUrl")
	    public String sessionTimeout() {
	        return "session-timeout";
	    }
}
