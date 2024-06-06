package com.boolstore.bookstoreapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boolstore.bookstoreapi.entites.Pret;

public interface PretRepository extends JpaRepository<Pret, Integer>{
	
	List<Pret> findByClientId(int clientId);
    List<Pret> findByLivreId(int livreId);

}
