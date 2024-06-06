package com.boolstore.bookstoreapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boolstore.bookstoreapi.entites.Auteur;
import com.boolstore.bookstoreapi.service.AuteurService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "auteur")
public class AuteurControlleur {
	
	 private AuteurService auteurService;

	    public AuteurControlleur(AuteurService auteurService) {
	        this.auteurService = auteurService;
	    }

	    
	    @ResponseStatus(value = HttpStatus.OK)
	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Auteur> getAllAuteurs() {
	        return auteurService.getAllAuteurs();
	    }
	    
	    @ResponseStatus(value = HttpStatus.OK)
	    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Auteur getAuteur(@PathVariable int id) {
	        return this.auteurService.getAuteur(id);
	    }
	    
	    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Auteur> updateAuteur(@PathVariable int id, @RequestBody Auteur auteurDetails) {
	    	Auteur updateAuteur = auteurService.updateAuteur(id, auteurDetails);
	        return ResponseEntity.ok(updateAuteur);
	    }
	    


}
