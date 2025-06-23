package com.example.demo.Controllers;

import com.example.demo.Entities.CustomerCreateRequest;
import com.example.demo.Entities.CustomerDto;
import com.example.demo.Entities.CustomerEntity;
import com.example.demo.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping({"/cust"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping({"/add"})
    public ResponseEntity<Boolean> addCustomer(@RequestBody CustomerCreateRequest request) {
        try {
            System.out.println(request.getEmail());
            CustomerEntity customer = new CustomerEntity(request.getEmail(),request.getPassword());
            return ResponseEntity.ok(this.customerService.addNewCustomer(customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }


    @PostMapping({"/login"})
    public ResponseEntity<Boolean> loginCustomer(@RequestBody CustomerCreateRequest request) {
        try {
            CustomerEntity customer = new CustomerEntity(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(this.customerService.loginCustomer(customer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping({"/"})
    public ResponseEntity<CustomerDto> getByEmail(@RequestBody Map<String,String> email) {
        try {
            CustomerDto customer = this.customerService.getByEmail(email.get("email"));
            if (customer == null)
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
