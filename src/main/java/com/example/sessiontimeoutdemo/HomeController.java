package com.example.sessiontimeoutdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	 @GetMapping("/")
	    public String home() {
	        return "home";
	    }

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