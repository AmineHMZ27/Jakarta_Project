package com.boolstore.bookstoreapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boolstore.bookstoreapi.entites.Category;
import com.boolstore.bookstoreapi.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public Category createCategory(Category category){
        return this.categoryRepository.save(category);
    }
    
    public List<Category> getAllCategories(){
    	return this.categoryRepository.findAll();
    }
    
    public Category getCategory(int id){
    	 Optional<Category> optionalCategory = this.categoryRepository.findById(id);
    	 return  optionalCategory.orElseThrow(() -> new EntityNotFoundException("Aucune Category n'existe avec cet id"));
    }
	
    public Category updateCategory(int id, Category categoryDetails) {
    	Category categoryDansLaBDD = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found for this id :: " + id));
   
    	categoryDansLaBDD.setNom(categoryDetails.getNom());
    	return categoryRepository.save(categoryDansLaBDD);
    }
    
    public String deleteCategory(int id) {
    	Category category = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found for this id :: " + id));
    	categoryRepository.delete(category);
    	
    	return "Category delete!";
    }


}
