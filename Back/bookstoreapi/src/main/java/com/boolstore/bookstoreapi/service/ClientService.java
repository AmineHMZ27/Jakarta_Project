package com.boolstore.bookstoreapi.service;

import com.boolstore.bookstoreapi.entites.Client;
import com.boolstore.bookstoreapi.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class ClientService {


    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    
    public Client createClient(Client client){
        return this.clientRepository.save(client);
    }
    
    public List<Client> getAllClients(){
    	return this.clientRepository.findAll();
    }
    
    public Client getClient(int id){
    	 Optional<Client> optionalClient = this.clientRepository.findById(id);
    	 return  optionalClient.orElseThrow(() -> new EntityNotFoundException("Aucun client n'existe avec cet id"));
    }
    
    public Client updateClient(int id, Client clientDetails) {
    	Client clientDansLaBDD = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found for this id :: " + id));
   
    		clientDansLaBDD.setNom(clientDetails.getNom());
    		clientDansLaBDD.setPrenom(clientDetails.getPrenom());
    		clientDansLaBDD.setEmail(clientDetails.getEmail());
    	return clientRepository.save(clientDansLaBDD);
    }
    
    public String deleteClient(int id) {
    	Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found for this id :: " + id));
    	clientRepository.delete(client);
    	
    	return "Customer delete!";
    }
}
