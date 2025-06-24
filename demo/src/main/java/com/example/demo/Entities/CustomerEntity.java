package com.example.demo.Entities;

import com.example.demo.Services.UniqueId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "customers"
)
@Setter
@Getter
@Data
public class CustomerEntity {
    public CustomerEntity() {
    }

    @Id
    private Long id;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    private String password;
    @OneToMany(
            mappedBy = "customer"
    )
    private List<AdvertisementEntity> advertisements;

    public CustomerEntity(Long id) {
        this.id = id;
    }

    public CustomerEntity(String email, String password) {
        this.id = UniqueId.generateUniqueId();
        this.email = email;
        this.password = password;
    }
}
