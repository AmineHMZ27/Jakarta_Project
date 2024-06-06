package com.boolstore.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.boolstore.bookstoreapi.entites.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Integer>{
	Optional<Auteur> findByNom(String nom);

}
