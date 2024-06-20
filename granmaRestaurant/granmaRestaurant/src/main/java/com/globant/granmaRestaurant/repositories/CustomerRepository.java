package com.globant.granmaRestaurant.repositories;

import com.globant.granmaRestaurant.model.entity.CustomerEntity;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@NonNullApi
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByDocument(String document);
    void deleteByDocument(String document);
    List<CustomerEntity> findByNameContainingIgnoreCase(String name);
    List<CustomerEntity> findAll(Sort sort);
}

// Optional<CustomerEntity> findByDeliveryAddress (String deliveryAddress)