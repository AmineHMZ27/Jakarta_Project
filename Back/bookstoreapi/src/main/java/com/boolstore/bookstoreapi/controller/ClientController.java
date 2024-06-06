package com.boolstore.bookstoreapi.controller;

import com.boolstore.bookstoreapi.entites.Client;
import com.boolstore.bookstoreapi.service.ClientService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Client createClient(@RequestBody Client client){

        return this.clientService.createClient(client);
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client lire(@PathVariable int id) {
        return this.clientService.getClient(id);
    }
    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client clientDetails) {
    	Client updatedClient = clientService.updateClient(id, clientDetails);
        return ResponseEntity.ok(updatedClient);
    }
    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public String deleteClient(@PathVariable int id){
    	return clientService.deleteClient(id);
    } 

}
