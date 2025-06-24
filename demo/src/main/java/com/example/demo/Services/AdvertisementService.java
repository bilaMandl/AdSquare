package com.example.demo.Services;

import com.example.demo.Entities.AdPackage;
import com.example.demo.Entities.AdvertisementDto;
import com.example.demo.Entities.AdvertisementEntity;
import com.example.demo.Entities.CustomerEntity;
import com.example.demo.Rpositorys.AdPackageRepository;
import com.example.demo.Rpositorys.AdvertisementRepository;
import com.example.demo.Rpositorys.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementService {
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private AdPackageRepository packageRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public boolean createAdvertisement(AdvertisementEntity adv, Long adPackageId, Long customerId) {
        AdvertisementEntity existingAd = advertisementRepository.findFirstByAreaNumberAndIsActiveTrue(adv.getAreaNumber());
        if(existingAd!=null){
            throw new IllegalStateException("There is already an active advertisement for this area");
        }
        AdPackage p = this.packageRepository.getById(adPackageId);
        CustomerEntity c = this.customerRepository.getById(customerId);
        if (p != null && c!=null){
            adv.setAdPackage(p);
            adv.setCustomer(c);
        }
        else if(p==null)
            throw new IllegalArgumentException("The package ID is undefined");
        else
            throw new IllegalArgumentException("The customer ID is undefined");
        AdvertisementEntity a = this.advertisementRepository.save(adv);
        if (a == null)
            return false;
        return true;
    }

    public String getActiveAdvertisements(int areaNumber) {
        AdvertisementEntity ad = this.advertisementRepository.findFirstByAreaNumberAndIsActiveTrue(areaNumber);
        if (ad == null)
            return "";
        LocalDateTime creationDate = ad.getCreationDate();
        long daysBetween = ChronoUnit.DAYS.between(creationDate, LocalDateTime.now());
        int targetDays = ad.getAdPackage().getNumberOfDays();
        if (ad.getClicks() < ad.getAdPackage().getNumberOfClicks() && daysBetween <= targetDays) {
            ad.setClicks(ad.getClicks()+1);
            advertisementRepository.save(ad);
            return ad.getImageUrl();
        } else {
            ad.setActive(false);
            advertisementRepository.save(ad);
            return "";
        }
    }

    public List<AdvertisementDto> findAll() {
        List<AdvertisementEntity> all = ((List<AdvertisementEntity>) this.advertisementRepository.findAll());
        List<AdvertisementDto> allAdv = new ArrayList<>();
        for (AdvertisementEntity entity : all) {
            AdvertisementDto dto = modelMapper.map(entity, AdvertisementDto.class);
            allAdv.add(dto);
        }
        return allAdv;
    }

}
