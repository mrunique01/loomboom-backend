package com.loomboom.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.loomboom.model.Contact;

public interface ContactService {

    List<Contact> getAllContact();

    Page<Contact> getAllContactByPage(Pageable pageable);

    Contact createContact(Contact contact);

    Contact updateContact(Contact contact, Long id);

    Boolean deleteContactById(Long id);

    Contact findByEmail(String email);

    Contact findById(Long id);
}