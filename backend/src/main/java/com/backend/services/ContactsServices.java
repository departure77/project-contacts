package com.backend.services;


import com.backend.dtos.ContactDto;
import com.backend.dtos.GetContactDto;
import com.backend.models.ContactsModels;
import com.backend.repository.ContactsRepository;
import com.backend.utils.ApiException;
import com.backend.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ContactsServices {

    @Autowired
    ContactsRepository contactsRepository;

    public GetContactDto pickContact(int idContact) {
        try {

            Optional<ContactsModels> contacto = contactsRepository.findById(idContact);

            if (contacto.isPresent()) {
                GetContactDto exit = new GetContactDto();
                exit.setIdContact(contacto.get().getIdContact());
                exit.setName(contacto.get().getName());
                exit.setSurname(contacto.get().getSurname());
                exit.setPhoneNumber(contacto.get().getPhoneNumber());
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }

    public Integer createContact (ContactDto entry) {
        try {

            Optional<ContactsModels> existingContact = contactsRepository.checkRepeatContact(entry.getPhoneNumber());
            if (existingContact.isPresent()) {
                throw new ApiException(409, "El usuario ya existe");
        }
    }
}
