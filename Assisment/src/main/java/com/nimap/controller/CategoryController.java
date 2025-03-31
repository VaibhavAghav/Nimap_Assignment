package com.nimap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import com.nimap.entity.Category;
import com.nimap.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	public CategoryService categoryServ;

	// Post adding Category
	@PostMapping
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		categoryServ.addCategory(category);
		return ResponseEntity.ok("Category added successfully");
	}

	// Put Updating Category
	@PutMapping
	public ResponseEntity<String> updateCategory(@RequestBody Category category) {
		categoryServ.updateCategory(category);
		return ResponseEntity.ok("Category Updated successfully");
	}

	// Get Get All Category
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategory(@RequestParam(defaultValue = "0") int page,
														 @RequestParam(defaultValue = "10") int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		List<Category> allCategory = categoryServ.getAllCategory(pageable);
		return ResponseEntity.ok(allCategory);
	}

	// Get get By ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") int id) {
		Optional<Category> category = categoryServ.getCategoryById(id);
		if (category.isEmpty()) {
			return ResponseEntity.status(404).body("Category with id " + id + " is not found");
		} else {
			return ResponseEntity.ok(category.get());
		}
	}

	// Delete delete by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
		boolean isDelete = categoryServ.deleteCategory(id);
		if (isDelete)
			return ResponseEntity.ok("Category deleted successfully");
		else
			return ResponseEntity.status(404).body("Category is not Found ");
	}

}
