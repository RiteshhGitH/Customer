package com.parabellum.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	
	@Autowired
	CustomerRepository customerRepo;
	
	public Customer getCustomerByEmail(String email, String password) {
		
		List<Customer> c = customerRepo.findByEmail(email);
		return c.get(0);
		
	}

	public Boolean addNewCustomer(String name,String email,String password) {
		
		Customer c = new Customer();
	    c.setName(name);
	    c.setEmail(email);
	    c.setPassword(password);
	    customerRepo.save(c);
	    return true;
		
	}
}
