package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.Contact;
import com.loomboom.repository.ContactRepository;
import com.loomboom.service.ContactService;
import lombok.RequiredArgsConstructor;
import static com.loomboom.utils.StringUtils.*;

import java.util.List;

import static com.loomboom.contants.ErrorMessage.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public Page<Contact> getAllContactByPage(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    public Contact createContact(Contact contact) {
        if (findByEmail(contact.getEmail()) != null) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact, Long id) {
        if (findById(id) == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        Contact duplicateContact = findByEmail(contact.getEmail());

        if (duplicateContact != null && !duplicateContact.getId().equals(id)) {
            throw new ApiRequestException(DUPLICATE_VALUE, HttpStatus.BAD_REQUEST);
        }
        contact.setId(id);
        return contactRepository.save(contact);
    }

    @Override
    public Boolean deleteContactById(Long id) {
        Contact contact = findById(id);
        if (contact == null) {
            throw new ApiRequestException(NOT_EXISTS, HttpStatus.BAD_REQUEST);
        }
        contactRepository.deleteById(id);
        return true;

    }

    @Override
    public Contact findByEmail(String email) {
        if (!empty(email)) {
            return contactRepository.findByEmail(email).orElse(null);
        }
        return null;
    }

    @Override
    public Contact findById(Long id) {
        if (!empty(id)) {
            return contactRepository.findById(id).orElse(null);
        }
        return null;
    }

}
