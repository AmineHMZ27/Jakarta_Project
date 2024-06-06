package com.boolstore.bookstoreapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boolstore.bookstoreapi.entites.Auteur;
import com.boolstore.bookstoreapi.entites.Category;
import com.boolstore.bookstoreapi.entites.Livre;
import com.boolstore.bookstoreapi.repository.AuteurRepository;
import com.boolstore.bookstoreapi.repository.CategoryRepository;
import com.boolstore.bookstoreapi.repository.LivreRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivreService {
	
	private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;
    private final CategoryRepository categoryRepository;

    public LivreService(LivreRepository livreRipository, AuteurRepository auteurRepository, CategoryRepository categoryRepository) {
        this.livreRepository = livreRipository;
        this.auteurRepository = auteurRepository;
        this.categoryRepository = categoryRepository;
    }
	
	
	public Livre createLivre(Livre livre){
		
	
		List<Livre> existingLivres = livreRepository.findByTitre(livre.getTitre());
	    if (!existingLivres.isEmpty()) {
	        throw new IllegalArgumentException("Le livre existe déjà.");
	    }
		    
	   
	    Auteur auteur = auteurRepository.findByNom(livre.getAuteur().getNom())
	            .orElseGet(() -> {
	                Auteur newAuteur = livre.getAuteur();
	                newAuteur.setLivres(new ArrayList<>());
	                return auteurRepository.save(newAuteur);
	            });

	    
	    Category category = categoryRepository.findByNom(livre.getCategory().getNom())
	            .orElseGet(() -> {
	                Category newCategory = livre.getCategory();
	                newCategory.setLivres(new ArrayList<>());
	                return categoryRepository.save(newCategory);
	            });

	    livre.setAuteur(auteur);
	    livre.setCategory(category);

	    auteur.getLivres().add(livre);
	    category.getLivres().add(livre);

	    return livreRepository.save(livre);
    }
    
    public List<Livre> getAllLivres(){
    	return this.livreRepository.findAll();
    }
    
    public Livre getLivre(int id){
    	 Optional<Livre> optionalLivre = this.livreRepository.findById(id);
    	 return  optionalLivre.orElseThrow(() -> new EntityNotFoundException("Aucun livre n'existe avec cet id"));
    }
	
    public Livre updateLivre(int id, Livre livreDetails) {
    	Livre livreDansLaBDD = livreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livre not found for this id :: " + id));

       
        livreDansLaBDD.setTitre(livreDetails.getTitre());

        
        Auteur auteur = auteurRepository.findByNom(livreDetails.getAuteur().getNom())
                .orElseGet(() -> auteurRepository.save(livreDetails.getAuteur()));

       
        Category category = categoryRepository.findByNom(livreDetails.getCategory().getNom())
                .orElseGet(() -> categoryRepository.save(livreDetails.getCategory()));

        livreDansLaBDD.setAuteur(auteur);
        livreDansLaBDD.setCategory(category);

        return livreRepository.save(livreDansLaBDD);
    }
    
    public String deleteLivre(int id) {
    	Livre livre = livreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("livre not found for this id :: " + id));
    	livreRepository.delete(livre);
    	
    	return "Livre delete!";
    }

}
