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

import com.nimap.entity.Product;
import com.nimap.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	public ProductService productServ ;
	
	//Post add product
	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		productServ.addProduct(product);
		return ResponseEntity.ok("Category added successfully");
	}
	
	// PUT update product
	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		productServ.updateProduct(product);
		return ResponseEntity.ok("Category Updated successfully");
	}
	
	//GET get all product
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam(defaultValue = "0") int page, 
													   @RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		List<Product> allProduct = productServ.getAllProduct(pageable);
		return ResponseEntity.ok(allProduct);
	}
	
	//GET get product by id
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") int id) {
		Optional<Product> product = productServ.getProductById(id);
		if(!product.isEmpty())
			return ResponseEntity.ok(product.get());
		else
			return ResponseEntity.status(404).body("Product with id "+id+" is not found");
	}
	
	//DELETE delete product
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
		boolean isProduct = productServ.deleteProduct(id);
		
		if(isProduct) {
			return ResponseEntity.ok("Product deleted successfully");
		}else {
			return ResponseEntity.status(404).body("product is not found");
		}
	}
	
}
