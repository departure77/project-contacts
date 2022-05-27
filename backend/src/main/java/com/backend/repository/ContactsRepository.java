package com.backend.repository;

import com.backend.models.ContactsModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<ContactsModels, Integer> {

    @Query(value = "select * from contactos where phone_number = ?1" , nativeQuery = true)
    Optional<ContactsModels> checkRepeatContact (String phoneNumber);

    @Query(value = "select * from contactos where id_usuario = ?1" , nativeQuery = true)
    List<ContactsModels> pickAllContacts (Integer idUser);


}
