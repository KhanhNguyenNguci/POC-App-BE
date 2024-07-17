package com.waytosucess41.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.waytosucess41.entity.IProduct;
import com.waytosucess41.exception.ResourceNotFoundException;
import com.waytosucess41.repository.ProductRepository;

@RestController // this annotation tell to spring container this class is request handler class

public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/products")
	public List<IProduct> getAllProducts(){
		return productRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/products")
	public IProduct createProduct(@RequestBody IProduct product) {
		return productRepository.save(product);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/products/{id}")
	public ResponseEntity<IProduct> getProductById(@PathVariable int id) {
		IProduct product = productRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Product not exist with id "+id));
		return ResponseEntity.ok(product);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/products/{id}")
	public ResponseEntity<IProduct> updateProduct(@PathVariable int id, @RequestBody IProduct productView){
		IProduct product = productRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Product not exist with id "+id));
		product.setProductName(productView.getProductName());
		product.setProductDescription(productView.getProductDescription());
		productRepository.save(product);
		
		return ResponseEntity.ok(product);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int id) {
		IProduct product = productRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Product not exist with id "+id));
		productRepository.delete(product);
		Map<String, Boolean> respone=new HashMap<String, Boolean>();
		respone.put("delete", Boolean.TRUE);
		return ResponseEntity.ok(respone);
	}
}
