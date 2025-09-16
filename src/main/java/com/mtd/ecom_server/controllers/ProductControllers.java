package com.mtd.ecom_server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.ecom_server.models.product;
import com.mtd.ecom_server.repos.ProductRepo;

@RestController
@RequestMapping("/products")
public class ProductControllers {
	@Autowired ProductRepo productRepo ;
	@GetMapping("/all")
	public List<product> getAllProducts() {
		return productRepo.findAll();
		
	}
@PostMapping("/add")
public product addProduct(@RequestBody product  newproduct ) {
	return productRepo.save(newproduct);
}

@DeleteMapping("/product/delete/{id}")
public String deleteProduct(@PathVariable String id) {
    product findproduct = productRepo.findById(id).get();
    if(findproduct != null) {
        productRepo.deleteById(id);
        return "Product Deleted " + findproduct.getName();
    } else {
        return "Failed to delete product";
    }
}

@PutMapping("/product/edit/{id}")
public product editProduct(@PathVariable String id, @RequestBody product newproduct) {
    product findproduct = productRepo.findById(id).get();
    findproduct.setName(newproduct.getName());
    findproduct.setDescription(newproduct.getDescription());
    findproduct.setCategory(newproduct.getCategory());
    findproduct.setTags(newproduct.getTags()); // comma-separated string
    findproduct.setPrice(newproduct.getPrice());
    findproduct.setStock(newproduct.getStock());
    return productRepo.save(findproduct);
    }
}