package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		
		List<Customer> custom=customerService.getCustomers();
		
		model.addAttribute("customers",custom);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String addCustomerForm(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String addCustomerData(@ModelAttribute("customer") Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	@RequestMapping("/formForUpdateCustomer")
	public String formForUpdateCustomerData(@RequestParam("customerId") int customerId,Model model) {
		Customer customer= customerService.getCustomer(customerId);
		
		model.addAttribute("customer",customer);
		
		return "customer-form";
	}
	
	@RequestMapping("/delete")
	public String deleteCustomerData(@RequestParam("customerId") int customerId) {
		
		customerService.deleteCustomer(customerId);
		
		return "redirect:/customer/list";
	}
}
