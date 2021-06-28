package com.backend.repository;

import com.backend.models.ContactsModels;
import com.backend.models.UserModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<ContactsModels, Integer> {

    @Query(value = "select * from contactos where phoneNumber = ?1" , nativeQuery = true)
    Optional<ContactsModels> checkRepeatContact (String phoneNumber);

}
