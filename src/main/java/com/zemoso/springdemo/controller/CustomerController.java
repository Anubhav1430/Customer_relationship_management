package com.zemoso.springdemo.controller;

import com.zemoso.springdemo.dao.CustomerDAO;
import com.zemoso.springdemo.entity.Customer;
import com.zemoso.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject the customer service in the controller
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String  listCustomers(Model theModel){

        //get customers from the dao
        List<Customer> theCustomers = customerService.getCustomers();

        //add customer to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        //create a model attribute to bind form data
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer" , theCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){

        //save the customer using our service
        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

        //get the customer from the service
        Customer theCustomer = customerService.getCustomer(theId);

        //set customer as a model attribute to pre-populated the form
        theModel.addAttribute("customer" , theCustomer);

        //send over the form
        return "customer-form";

    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId")int theId){

        //delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }

}
