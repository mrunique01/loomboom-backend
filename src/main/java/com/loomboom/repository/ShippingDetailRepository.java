package com.loomboom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loomboom.model.ShippingDetail;

@Repository
public interface ShippingDetailRepository extends JpaRepository<ShippingDetail, Long> {

}
