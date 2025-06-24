package com.example.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdPackageDto {
    private Long id;
    private int numberOfClicks;
    private int numberOfDays;
}
