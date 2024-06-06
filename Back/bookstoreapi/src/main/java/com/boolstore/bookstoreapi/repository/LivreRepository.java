package com.boolstore.bookstoreapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boolstore.bookstoreapi.entites.Livre;

public interface LivreRepository extends JpaRepository<Livre, Integer>{
	List<Livre> findByTitre(String titre);
	

}
