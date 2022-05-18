package com.yetel.demo.Repositories;

import com.yetel.demo.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName);
    Optional<Client> findClientByEgn(String egn);



    }
