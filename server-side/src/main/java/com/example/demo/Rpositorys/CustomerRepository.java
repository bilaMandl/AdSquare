
package com.example.demo.Rpositorys;

import com.example.demo.Entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    boolean existsByEmail(String email);

    CustomerEntity getById(Long id);

    CustomerEntity findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);
}
