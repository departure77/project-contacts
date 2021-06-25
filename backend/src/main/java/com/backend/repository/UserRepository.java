package com.backend.repository;

import com.backend.models.UserModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserModels, Integer> {

    @Query(value = "select * from usuarios where username = ?1 and mail = ?2" , nativeQuery = true)
    Optional<UserModels> chekRepeatUser (String username, String mail);
}
