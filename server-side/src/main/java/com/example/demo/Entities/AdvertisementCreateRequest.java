package com.example.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Setter
@Getter
public class AdvertisementCreateRequest {
        private int clicks;
        private int areaNumber;
        private Long adPackageId;
        private Long customerId;
}
