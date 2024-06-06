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

import com.boolstore.bookstoreapi.entites.Category;
import com.boolstore.bookstoreapi.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "category")
public class CategoryControlleur {
	
    private CategoryService categoryService;

    public CategoryControlleur(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category createCategory(@RequestBody Category category){

        return this.categoryService.createCategory(category);
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getCategory(@PathVariable int id) {
        return this.categoryService.getCategory(id);
    }
    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category categoryDetails) {
    	Category updateCategory = categoryService.updateCategory(id, categoryDetails);
        return ResponseEntity.ok(updateCategory);
    }
    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public String deleteCategory(@PathVariable int id){
    	return categoryService.deleteCategory(id);
    } 


}
