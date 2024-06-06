package com.boolstore.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.boolstore.bookstoreapi.entites.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Optional<Category> findByNom(String nom);

}
