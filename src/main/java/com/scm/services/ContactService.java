package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import com.scm.entities.Contact;
import com.scm.entities.user;

public interface ContactService {
    //seve contas
    Contact save(Contact contact);

    ///update contact
    Contact update(Contact contact);

    //get contats
    List<Contact>getAll();
    //get contact by id
    Contact getById(String id);
    //delete contact
    void delete(String id);
    //sech contact
    Page<Contact> searchByName(String nameKeyword ,int size, int page ,String sortBy, String order,user user);
    Page<Contact> searchByEmail(String emailKeyword ,int size, int page ,String sortBy, String order,user user);
    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword ,int size, int page ,String sortBy, String order,user user);
    //get contact by User Id
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(user user,int page,int size, String sortField, String sortDirection);

}
