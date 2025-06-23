
package com.example.demo.Services;

import com.example.demo.Entities.AdvertisementDto;
import com.example.demo.Entities.CustomerDto;
import com.example.demo.Entities.CustomerEntity;
import com.example.demo.Rpositorys.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public boolean addNewCustomer(CustomerEntity customer) {
        if (this.customerRepository.existsByEmail(customer.getEmail())) {
            return false;
        } else {
            this.customerRepository.save(customer);
            return true;
        }
    }

    public CustomerEntity getById(Long id) {
        return this.customerRepository.getById(id);
    }

    public boolean loginCustomer(CustomerEntity customer) {
        return this.customerRepository.existsByEmailAndPassword(customer.getEmail(), customer.getPassword());
    }

    public CustomerDto getByEmail(String email) {
        CustomerEntity c = this.customerRepository.findByEmail(email);
        return modelMapper.map(c, CustomerDto.class);
    }
}
