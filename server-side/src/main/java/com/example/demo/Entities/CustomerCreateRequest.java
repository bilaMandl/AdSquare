package com.example.demo.Entities;

import com.example.demo.Services.UniqueId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CustomerCreateRequest {
    private String email;
    private String password;

    public CustomerCreateRequest() {
    }

    public CustomerCreateRequest(String email) {
        this.email = email;
    }
    public CustomerCreateRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
