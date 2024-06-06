package com.boolstore.bookstoreapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boolstore.bookstoreapi.entites.Client;
import com.boolstore.bookstoreapi.entites.Livre;
import com.boolstore.bookstoreapi.entites.Pret;
import com.boolstore.bookstoreapi.repository.PretRepository;
import com.boolstore.bookstoreapi.repository.LivreRepository;
import com.boolstore.bookstoreapi.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PretService {
	
	private final PretRepository pretRepository;
	private final LivreRepository livreRepository;
	private final ClientRepository clientRepository;

	public PretService(PretRepository pretRepository, LivreRepository livreRepository, ClientRepository clientRepository) {
		this.pretRepository = pretRepository;
		this.livreRepository = livreRepository;
		this.clientRepository = clientRepository;
	}

	



	public Pret initierPret(String titreLivre, String nomClient) {
	
	    Livre livre = livreRepository.findByTitre(titreLivre)
	            .stream()
	            .findFirst()
	            .orElseThrow(() -> new EntityNotFoundException("Livre not found with title: " + titreLivre));

	  
	    Client client = clientRepository.findByNom(nomClient)
	            .orElseThrow(() -> new IllegalArgumentException("Client not found with name: " + nomClient + ". Please create an account."));

	   
	    String clientInfo = String.format("Client found: %s %s, Email: %s", client.getNom(), client.getPrenom(), client.getEmail());
	    System.out.println(clientInfo);

	 
	    Pret pret = new Pret();
	    pret.setDateDebut(LocalDate.now());
	    pret.setDateFin(LocalDate.now().plusWeeks(2)); 
	    pret.setClient(client);
	    pret.setLivre(livre);

	    return pretRepository.save(pret);
    }
	
    public List<Pret> getAllPrets(){
    	return this.pretRepository.findAll();
    }
    
    public Pret getPret(int id){
    	 Optional<Pret> optionalPret = this.pretRepository.findById(id);
    	 return  optionalPret.orElseThrow(() -> new EntityNotFoundException("Aucun Pret n'existe avec cet id"));
    }
    
    public List<Pret> getPretsByClientId(int clientId) {
        return pretRepository.findByClientId(clientId);
    }

    public List<Pret> getPretsByLivreId(int livreId) {
        return pretRepository.findByLivreId(livreId);
    }
	
    public Pret updatePret(int id, Pret pretDetails) {
        Pret pret = pretRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pret not found for this id :: " + id));

        pret.setDateDebut(pretDetails.getDateDebut());
        pret.setDateFin(pretDetails.getDateFin());
        pret.setClient(pretDetails.getClient());
        pret.setLivre(pretDetails.getLivre());

        return pretRepository.save(pret);
    }

    
    
    public String deletePret(int id) {
    	Pret pret = pretRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pret not found for this id :: " + id));
    	pretRepository.delete(pret);
    	
    	return "Customer delete!";
    }

}
