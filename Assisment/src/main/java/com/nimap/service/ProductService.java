package com.nimap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nimap.entity.Product;
import com.nimap.repository.CategoryRepository;
import com.nimap.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	public ProductRepository productRepo;

	@Autowired
	public CategoryRepository categoryRepo;

	public void addProduct(Product product) {
		System.out.println(product.getCategory().getCategoryId());
		productRepo.save(product);
	}

	public void updateProduct(Product product) {
		productRepo.save(product);
	}

	public Optional<Product> getProductById(int id) {
		Optional<Product> product = productRepo.findById(id);
		return product;
	}

	//Pagable
	
	public List<Product> getAllProduct(Pageable page) {
		List<Product> allProduct = productRepo.findAll(page).getContent();
		return allProduct;
	}
	
	
	public boolean deleteProduct(int id) {

		Optional<Product> product = productRepo.findById(id);
		if (!product.isEmpty()) {
			productRepo.delete(product.get());
			return true;
		} else {
			System.out.println("Not found");
			return false;
		}
	}
}
