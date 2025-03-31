package com.nimap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nimap.entity.Category;
import com.nimap.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
	

	@Autowired
	public CategoryRepository categoryRepo ;
	
	
	// Add Category
	@Transactional
	public void addCategory(Category category) {
		System.out.println("Inside service");
		categoryRepo.save(category);
		System.out.println(" after Inside service");
	}
	
	// Update Category
	public void updateCategory(Category category) {
		categoryRepo.save(category);
	}
	
	// Pageable 
	// Get All Category
	public  List<Category> getAllCategory(Pageable page){
		List<Category> allCategory = categoryRepo.findAll(page).getContent();
		return allCategory;
	}

	
	
	// Get Category by id
	public Optional<Category> getCategoryById(int id) {
		System.out.println("Inside service before find");
		Optional<Category> category = categoryRepo.findById(id);
		System.out.println("Inside service after find");
		return category;
	}
	
	// Delete By Id
	@Transactional
	public boolean deleteCategory(int id ) {
		Optional<Category> isCategory = categoryRepo.findById(id);
		if(!isCategory.isEmpty()) {
			categoryRepo.delete(isCategory.get());
			return true;
		}else {
			return false;
		}
	}
	
	

}
