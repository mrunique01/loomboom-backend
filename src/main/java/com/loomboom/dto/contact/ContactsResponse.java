package com.loomboom.dto.contact;

import java.util.List;

import com.loomboom.dto.pagination.PaginationReponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactsResponse {
    List<ContactResponse> contacts;
    PaginationReponse pagination;
}
