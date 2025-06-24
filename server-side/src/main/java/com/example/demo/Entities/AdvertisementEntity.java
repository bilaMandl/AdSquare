package com.example.demo.Entities;
import com.example.demo.Services.UniqueId;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Table(
        name = "advertisements"
)
@Entity
@Getter
@Setter
public class AdvertisementEntity {
    @Id
    private Long id;
    private int clicks;
    private int areaNumber;
    private boolean isActive;
    private LocalDateTime creationDate;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(
            name = "customerId"
    )
    CustomerEntity customer;
    @ManyToOne
    @JoinColumn(
            name = "adPackageId"
    )
    AdPackage adPackage;

    public AdvertisementEntity() {
        id = UniqueId.generateUniqueId();
    }

    public AdvertisementEntity( int clicks, int areaNumber, String imageUrl,  Long adPackageId, Long customerId) {
        this();
        this.clicks = clicks;
        this.areaNumber = areaNumber;
        this.isActive = true;
        this.creationDate = LocalDateTime.now();
        this.imageUrl = imageUrl;
       
    }

}
