package com.example.demo.Services;

import com.example.demo.Entities.AdPackage;
import com.example.demo.Entities.AdPackageDto;
import com.example.demo.Rpositorys.AdPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdPackageService {
    @Autowired
    private AdPackageRepository adPackageRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    public Iterable<AdPackageDto> getPackages() {
        Iterable<AdPackage> packages = this.adPackageRepository.findAll();
        List<AdPackageDto> p = new ArrayList<>();
        for (AdPackage adPackage : packages) {
            p.add(modelMapper.map(adPackage, AdPackageDto.class));
        }
        return p;
    }

    public AdPackage getById(Long id) {
        return this.adPackageRepository.getById(id);
    }

    public boolean insertNewPackage(AdPackage p) {
        try {
            Optional<AdPackage> existing = this.adPackageRepository.findByNumberOfClicksAndNumberOfDays(
                    p.getNumberOfClicks(),
                    p.getNumberOfDays()
            );

            if (existing.isPresent()) {
                throw new Exception("The package exist");
            } else {
                System.out.println(this.adPackageRepository.save(p));
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
