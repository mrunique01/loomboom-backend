package com.loomboom.repository;

import org.springframework.stereotype.Repository;
import com.loomboom.model.Discount;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Optional<Discount> findByTitle(String title);
}
