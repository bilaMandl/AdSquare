package com.example.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AdPackageCreateRequest {
    private int numberOfClicks;
    private int numberOfDays;
}
