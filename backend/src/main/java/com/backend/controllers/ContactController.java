package com.backend.controllers;

import com.backend.dtos.ContactDto;
import com.backend.dtos.GetContactDto;
import com.backend.services.ContactsServices;
import com.backend.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    ContactsServices contactsServices;

    @GetMapping("")
    public ResponseEntity<GetContactDto> pickContact(@RequestParam Integer idContact) {

        try {
            GetContactDto exit = contactsServices.pickContact(idContact);
            return new ResponseEntity<>(exit, HttpStatus.OK);

        } catch (ApiException error) {
            switch (error.getCode()) {
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR : " + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("")
    public ResponseEntity<Integer> createContact(@RequestBody ContactDto body)


}
