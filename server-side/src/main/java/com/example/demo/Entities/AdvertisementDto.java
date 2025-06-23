package com.example.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Setter
@Getter
public class AdvertisementDto {
    private Long id;
    private int clicks;
    private int areaNumber;
    private boolean isActive;
    private LocalDateTime creationDate;
    private String imageUrl;
    private Long adPackageId;
    private Long customerId;

}