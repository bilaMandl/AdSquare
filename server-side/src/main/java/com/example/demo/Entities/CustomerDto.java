package com.example.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CustomerDto {
    private Long id;
    private String email;
    private String password;
}
