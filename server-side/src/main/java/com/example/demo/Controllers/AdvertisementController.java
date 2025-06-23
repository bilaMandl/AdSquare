package com.example.demo.Controllers;

import com.example.demo.Entities.AdvertisementCreateRequest;
import com.example.demo.Entities.AdvertisementDto;
import com.example.demo.Entities.AdvertisementEntity;
import com.example.demo.Services.AdvertisementService;
import com.example.demo.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping({"/adv"})
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private ImageService imageService;

    @PostMapping({"/"})
    public ResponseEntity<?> createAdvertisement(@ModelAttribute AdvertisementCreateRequest request, @RequestParam("file") MultipartFile file) {
        try {
            Long adPackageId = request.getAdPackageId();
            Long customerId = request.getCustomerId();
            String imageUrl = imageService.saveImage(file);
            AdvertisementEntity advertisement = new AdvertisementEntity(request.getClicks(), request.getAreaNumber(), imageUrl, adPackageId, customerId);
            if (!this.advertisementService.createAdvertisement(advertisement, adPackageId, customerId))
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error:"+e.getMessage());
        }
    }

    @GetMapping({"/{areaNumber}"})
    public ResponseEntity<String> getActiveAdvertisementsImage(@PathVariable int areaNumber) {
        try {
            String imageUrl = this.advertisementService.getActiveAdvertisements(areaNumber);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping({"/all"})
    public ResponseEntity<List<AdvertisementDto>> getAll() {
        try {
            List<AdvertisementDto> all = this.advertisementService.findAll();
            if (!all.isEmpty())
                return ResponseEntity.ok(all);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
