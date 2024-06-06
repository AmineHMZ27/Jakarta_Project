package com.boolstore.bookstoreapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boolstore.bookstoreapi.entites.Livre;
import com.boolstore.bookstoreapi.service.LivreService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "livre")
public class LivreController {
	
	  private LivreService livreService;

	    public LivreController(LivreService livreService) {
	        this.livreService = livreService;
	    }

	    @ResponseStatus(value = HttpStatus.CREATED)
	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public Livre createLivre(@RequestBody Livre livre){

	        return this.livreService.createLivre(livre);
	    }
	    
	    @ResponseStatus(value = HttpStatus.OK)
	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Livre> getAllLivres() {
	        return livreService.getAllLivres();
	    }
	    
	    @ResponseStatus(value = HttpStatus.OK)
	    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Livre getLivre(@PathVariable int id) {
	        return this.livreService.getLivre(id);
	    }
	    
	    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Livre> updateLivre(@PathVariable int id, @RequestBody Livre LivreDetails) {
	    	Livre updateLivre = livreService.updateLivre(id, LivreDetails);
	        return ResponseEntity.ok(updateLivre);
	    }
	    
	    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	    @DeleteMapping(path = "{id}")
	    public String deleteLivre(@PathVariable int id){
	    	return livreService.deleteLivre(id);
	    } 

}
