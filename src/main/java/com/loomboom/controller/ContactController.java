package com.loomboom.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import static com.loomboom.contants.PathConstants.*;
import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.contact.ContactRequest;
import com.loomboom.dto.contact.ContactResponse;
import com.loomboom.dto.contact.ContactsResponse;
import com.loomboom.mapper.ContactMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactMapper contactMapper;

    @GetMapping(ALL_CONTACTS)
    public ResponseEntity<ContactsResponse> getContacts() {
        ContactsResponse contacts = contactMapper.getAllContact();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping(ALL_CONTACTS_BY_PAGE)
    public ResponseEntity<ContactsResponse> getContactsByPage(Pageable pageble) {
        ContactsResponse contacts = contactMapper.getAllContactByPage(pageble);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping(CONTACT_BY_ID)
    public ResponseEntity<ContactResponse> getContactById(@PathVariable Long contactId) {
        ContactResponse contact = contactMapper.findContactById(contactId);
        return ResponseEntity.ok(contact);
    }

    @PostMapping(CREATE_CONTACT)
    public ResponseEntity<ContactResponse> createContact(
            @RequestBody @Valid ContactRequest contactRequest) {
        ContactResponse contactResponse = contactMapper.createContact(contactRequest);
        return ResponseEntity.ok(contactResponse);
    }

    @PutMapping(UPDATE_CONTACT)
    public ResponseEntity<ContactResponse> updateContact(@PathVariable Long contactId,
            @RequestBody @Valid ContactRequest contactRequest) {

        ContactResponse contact = contactMapper.updateContact(contactId, contactRequest);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping(DELETE_CONTACT)
    public ResponseEntity<ApiResponse> deleteContact(@PathVariable Long contactId) {
        ApiResponse response = contactMapper.deleteContact(contactId);
        return ResponseEntity.ok(response);
    }

}
