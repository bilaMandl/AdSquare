package com.example.demo.Rpositorys;

import com.example.demo.Entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdvertisementRepository extends CrudRepository<AdvertisementEntity, Long> {
    AdvertisementEntity findFirstByAreaNumberAndIsActiveTrue(int areaNumber);

    AdvertisementEntity findById(int id);

    Iterable<AdvertisementEntity> findAll();
}
