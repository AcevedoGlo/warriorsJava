package com.globant.granmaRestaurant.repositories;

import com.globant.granmaRestaurant.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByDocument(String document);
    void deleteByDocument(String document);
}

// Optional<CustomerEntity> findByDeliveryAddress (String deliveryAddress)