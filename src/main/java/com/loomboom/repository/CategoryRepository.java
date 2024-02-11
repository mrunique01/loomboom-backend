package com.loomboom.repository;

import org.springframework.stereotype.Repository;

import com.loomboom.model.Category;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String title);
}
