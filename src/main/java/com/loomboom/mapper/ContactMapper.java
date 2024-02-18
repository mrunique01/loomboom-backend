package com.loomboom.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.dto.api.ApiResponse;
import com.loomboom.dto.pagination.PaginationReponse;
import com.loomboom.dto.contact.ContactsResponse;
import com.loomboom.dto.contact.ContactRequest;
import com.loomboom.dto.contact.ContactResponse;
import com.loomboom.model.Contact;
import static com.loomboom.contants.SuccessMessage.*;
import com.loomboom.service.ContactService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ContactMapper {

    private final ContactService contactService;
    private final CommonMapper commonMapper;

    public ContactResponse createContact(ContactRequest contactRequest) {

        Contact contact = commonMapper.mapObject(contactRequest, Contact.class);
        return commonMapper.mapObject(contactService.createContact(contact), ContactResponse.class);
    }

    public ContactsResponse getAllContact() {
        List<Contact> contact = contactService.getAllContact();
        List<ContactResponse> contacts = commonMapper.mapListObject(contact, ContactResponse.class);
        ContactsResponse contactResponse = new ContactsResponse();
        contactResponse.setContacts(contacts);
        contactResponse.setPagination(new PaginationReponse());
        return contactResponse;
    }

    public ContactsResponse getAllContactByPage(Pageable pageable) {
        Page<Contact> contact = contactService.getAllContactByPage(pageable);
        List<ContactResponse> contacts = commonMapper.mapListObject(contact.getContent(), ContactResponse.class);
        ContactsResponse contactResponse = new ContactsResponse();
        contactResponse.setContacts(contacts);
        PaginationReponse paginationReponse = commonMapper.paginationData(contact);
        contactResponse.setPagination(paginationReponse);
        return contactResponse;
    }

    public ContactResponse findContactById(Long id) {
        return commonMapper.mapObject(contactService.findById(id), ContactResponse.class);
    }

    public ContactResponse updateContact(Long id, ContactRequest contactRequest) {
        Contact contact = commonMapper.mapObject(contactRequest, Contact.class);
        return commonMapper.mapObject(contactService.updateContact(contact, id), ContactResponse.class);
    }

    public ApiResponse deleteContact(Long id) {
        contactService.deleteContactById(id);
        return new ApiResponse(true, CATEGORY_DELETED);
    }
}
