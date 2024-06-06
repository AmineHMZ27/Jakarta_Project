package com.boolstore.bookstoreapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boolstore.bookstoreapi.entites.Auteur;
import com.boolstore.bookstoreapi.repository.AuteurRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuteurService {
	
	private final AuteurRepository auteurRepository;

	public AuteurService(AuteurRepository auteurRepository) {
		this.auteurRepository = auteurRepository;
	}
	
	
    public List<Auteur> getAllAuteurs(){
    	return this.auteurRepository.findAll();
    }
    
    public Auteur getAuteur(int id){
    	 Optional<Auteur> optionalAuteur = this.auteurRepository.findById(id);
    	 return  optionalAuteur.orElseThrow(() -> new EntityNotFoundException("Aucun Auteur n'existe avec cet id"));
    }
	
    public Auteur updateAuteur(int id, Auteur auteurDetails) {
    	Auteur auteurDansLaBDD = auteurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Auteur not found for this id :: " + id));
   
    	auteurDansLaBDD.setNom(auteurDetails.getNom());
    	return auteurRepository.save(auteurDansLaBDD);
    }


}
