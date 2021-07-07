package com.backend.services;


import com.backend.dtos.ContactDto;
import com.backend.dtos.GetContactDto;
import com.backend.models.ContactsModels;
import com.backend.repository.ContactsRepository;
import com.backend.utils.ApiException;
import com.backend.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
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

                return exit;
            }else{
                throw new ApiException(404, "El contacto no existe");
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

            if (entry.getName().length() <= 50 && entry.getSurname().length() <= 50
                    && entry.getPhoneNumber().length() <= 50){

                ContactsModels contact = new ContactsModels();
                contact.setName(entry.getName());
                contact.setSurname(entry.getSurname());
                contact.setPhoneNumber(entry.getPhoneNumber());

                contact = contactsRepository.save(contact);
                return contact.getIdContact();

            } else {
                throw new ApiException(400, "Los datos enviados no son validos.");
            }

        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }
}
