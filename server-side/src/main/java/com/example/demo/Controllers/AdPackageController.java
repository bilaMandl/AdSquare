
package com.example.demo.Controllers;

import com.example.demo.Entities.AdPackage;
import com.example.demo.Entities.AdPackageCreateRequest;
import com.example.demo.Entities.AdPackageDto;
import com.example.demo.Services.AdPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/pack"})
public class AdPackageController {
    @Autowired
    private AdPackageService adPackageService;

    @GetMapping({"/"})
    public ResponseEntity<List<AdPackageDto>> getAllPackages() {
        try {
            List<AdPackageDto> packs = (List<AdPackageDto>) this.adPackageService.getPackages();
            if (packs.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(packs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping({"/"})
    public ResponseEntity<?> insertNewPackage(@RequestBody AdPackageCreateRequest request) {
        try {
            AdPackage pack = new AdPackage(request.getNumberOfClicks(), request.getNumberOfDays());
            boolean f = this.adPackageService.insertNewPackage(pack);
            System.out.println(f);
            if (f)
                return ResponseEntity.ok(f);
            else
                throw new Exception("Error in insert new package");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
