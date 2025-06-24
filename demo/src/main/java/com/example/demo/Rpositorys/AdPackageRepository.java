package com.example.demo.Rpositorys;

import com.example.demo.Entities.AdPackage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdPackageRepository extends CrudRepository<AdPackage, Long> {
    Optional<AdPackage> findByNumberOfClicksAndNumberOfDays(int numberOfClicks, int numberOfDays);
    AdPackage getById(Long id);
}
