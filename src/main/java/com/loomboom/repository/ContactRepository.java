package com.loomboom.repository;

import org.springframework.stereotype.Repository;
import com.loomboom.model.Contact;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByEmail(String email);
}
