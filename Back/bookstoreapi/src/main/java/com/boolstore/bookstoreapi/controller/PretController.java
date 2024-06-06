package com.boolstore.bookstoreapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.boolstore.bookstoreapi.entites.Pret;
import com.boolstore.bookstoreapi.service.PretService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "pret")
public class PretController {

	private PretService pretService;

    public PretController(PretService pretService) {
        this.pretService = pretService;
    }
    
    
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pret> initierPret(@RequestParam String titreLivre, @RequestParam String nomClient) {
    	Pret pret = pretService.initierPret(titreLivre, nomClient);
        return ResponseEntity.ok(pret);
    }
    
    
    
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pret> getAllPrets() {
        return pretService.getAllPrets();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pret getPret(@PathVariable int id) {
        return this.pretService.getPret(id);
    }
    

   
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path="/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pret> getPretsByClientId(@PathVariable int clientId) {
        return pretService.getPretsByClientId(clientId);
    }

    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path="/livre/{livreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pret> getPretsByLivreId(@PathVariable int livreId) {
        return pretService.getPretsByLivreId(livreId);
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Pret> updatePret(@PathVariable int id, @RequestBody Pret pretDetails) {
        Pret updatedPret = pretService.updatePret(id, pretDetails);
        return ResponseEntity.ok(updatedPret);
    }

    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public String deleteClient(@PathVariable int id){
    	return pretService.deletePret(id);
    } 

}
