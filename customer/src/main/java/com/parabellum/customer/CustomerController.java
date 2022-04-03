package com.parabellum.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
    public String index() {
        return "index";
    }
	
	@GetMapping("/login")
	public String getCustomerByEmail(
			@RequestParam(name = "email", required = false, defaultValue = "") String email,
			@RequestParam(name = "password", required = false, defaultValue = "") String password, 
			Model model) {
	
		Customer c = customerService.getCustomerByEmail(email, password);
		model.addAttribute("name", c.getName());
		if(password.equals(c.getPassword())) {
			return "login";		
		}
        return "error";		
	}
	
	@PostMapping("/register")
	@ResponseBody
	public String addNewCustomer (@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		Boolean status = customerService.addNewCustomer(name,email,password);
		if(status) {
			return "index";
		}
	    return "error";
	  }
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

}
