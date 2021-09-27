package com.backend.services;


import com.backend.dtos.ContactDto;
import com.backend.dtos.GetContactDto;
import com.backend.models.ContactsModels;
import com.backend.models.UserModels;
import com.backend.repository.ContactsRepository;
import com.backend.repository.UserRepository;
import com.backend.utils.ApiException;
import com.backend.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactsServices {

    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    UserRepository userRepository;

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
            } else {
                throw new ApiException(404, "El contacto no existe");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }

    public List<GetContactDto> pickAllContacts(int idUser) {
        try {
            List<ContactsModels> contactos = contactsRepository.pickAllContacts(idUser);
            List<GetContactDto> exit = new ArrayList<>();
            for (ContactsModels it : contactos) {
                GetContactDto dto = new GetContactDto();
                dto.setIdContact(it.getIdContact());
                dto.setName(it.getName());
                dto.setSurname(it.getSurname());
                dto.setPhoneNumber(it.getPhoneNumber());

                exit.add(dto);
            }
            return exit;
        } catch (ApiException error) {
            throw new ApiException(404, "la lista no existe");
        }
    }

    public Integer createContact(ContactDto entry) {
        try {

            Optional<UserModels> loggedUser = userRepository.findById(entry.getIdUser());

            if (!loggedUser.isPresent()) {
                throw new ApiException(404, "El usuario no existe.");
            }


            Optional<ContactsModels> existingContact = contactsRepository.checkRepeatContact(entry.getPhoneNumber());
            if (existingContact.isPresent()) {
                throw new ApiException(409, "El contacto ya existe");
            }

            if (entry.getName().length() <= 50 && entry.getSurname().length() <= 50
                    && entry.getPhoneNumber().length() <= 50) {

                ContactsModels contact = new ContactsModels();
                contact.setName(entry.getName());
                contact.setSurname(entry.getSurname());
                contact.setPhoneNumber(entry.getPhoneNumber());

                contact.setUsuario(loggedUser.get());
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

    public void deleteContact(Integer idContact) {

        try {
            if (contactsRepository.existsById(idContact)) {
                contactsRepository.deleteById(idContact);
            } else {
                throw new ApiException(404, "El contacto ya no existe...");
            }
        }catch (ApiException error){
            throw error;
        }catch (Exception error){
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }

    public int refreshContact (int idContact, ContactDto body){
        try{
            Optional<ContactsModels> contactDB = contactsRepository.findById(idContact);

            if (contactDB.isPresent()){
                ContactsModels in = contactDB.get();

                if (body.getName() != null){
                    in.setName(body.getName());
                }
                if (body.getSurname() != null){
                    in.setSurname(body.getSurname());
                }
                if (body.getPhoneNumber() != null){
                    in.setPhoneNumber(body.getPhoneNumber());
                }

                contactsRepository.save(in);
                return in.getIdContact();
            } else {
                throw new ApiException(404, Constantes.ERROR_NO_EXISTE);
            }
        }catch (ApiException error){
            throw error;
        } catch (Exception error){
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }
}
