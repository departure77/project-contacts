package com.backend.controllers;

import com.backend.dtos.GetUserDto;
import com.backend.dtos.PutUserDto;
import com.backend.dtos.UserDto;
import com.backend.services.ContactsServices;
import com.backend.services.UserService;
import com.backend.utils.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<GetUserDto> pickUser(@RequestParam Integer idUser) {

        try {
            GetUserDto exit = userService.pickUser(idUser);
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

    @PostMapping("")
    public ResponseEntity<Integer> createUser(@RequestBody UserDto body) {

        try{
            Integer user = userService.createUser(body);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ApiException error){
            switch (error.getCode()){
                case 409:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                case 400:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                default:
                    log.error("ERROR : " + error.getMessage(), error);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser(@RequestParam Integer idUser){

        try{
            userService.deleteUser(idUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ApiException error){
            switch (error.getCode()){
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("")
    public ResponseEntity<Integer> refreshUser (@RequestParam Integer idUser, @RequestBody PutUserDto body){

        try{
            int exit = userService.refreshUser(idUser, body);
            return new ResponseEntity<>(exit, HttpStatus.OK);

        } catch (ApiException error) {
            switch (error.getCode()){
                case 404:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                default:
                    log.error("ERROR : " + error.getMessage());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }
    }

}
