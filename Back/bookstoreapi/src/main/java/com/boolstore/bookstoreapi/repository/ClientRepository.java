package com.boolstore.bookstoreapi.repository;

import com.boolstore.bookstoreapi.entites.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	Optional<Client> findByNom(String nom);

}
