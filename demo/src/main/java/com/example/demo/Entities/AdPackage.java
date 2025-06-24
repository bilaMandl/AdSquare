package com.example.demo.Entities;

import com.example.demo.Services.UniqueId;
import jakarta.persistence.*;

import java.util.List;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "adPackages"
)
@Setter
@Getter
public class AdPackage {

    @Id
    private Long id;
    private int numberOfClicks;
    private int numberOfDays;
    @OneToMany(
            mappedBy = "adPackage"
    )
    List<AdvertisementEntity> advertisements;


    public AdPackage(){
        id = UniqueId.generateUniqueId();
    }
    public AdPackage(int numberOfClicks, int numberOfDays) {
        this();
        this.setNumberOfDays(numberOfDays);
        this.setNumberOfClicks(numberOfClicks);
    }
    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "AdPackage(id=" + var10000 + ", numberOfClicks=" + this.getNumberOfClicks() + ", numberOfDays=" + this.getNumberOfDays() + ", advertisements=" + String.valueOf(this.getAdvertisements()) + ")";
    }

}
