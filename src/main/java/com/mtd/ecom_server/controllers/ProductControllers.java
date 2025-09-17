package com.mtd.ecom_server.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
public class ProductControllers {
	private static final Logger Log=LoggerFactory.getLogger(ProductControllers.class);
	@Autowired ProductRepo productRepo ;
	@Tag(name="Get All Products")
	@GetMapping("/all")
	public List<product> getAllProducts() {
		Log.info("fetching products");
		return productRepo.findAll();
		
	}
@PostMapping("/add")
public product addProduct(@RequestBody product  newproduct ) {
	Log.info(" adding Product"+ newproduct);

	return productRepo.save(newproduct);
}

@DeleteMapping("/product/delete/{id}")
public String deleteProduct(@PathVariable String id) {
    Optional<product> productToDelete = productRepo.findById(id);
    if(productToDelete.isEmpty()) {
        return "Failed to delete product";
    }
    productRepo.deleteById(id);
    Log.info("Product Deleted"+ id);
    return "product deleted";

}

@PutMapping("/product/edit/{id}")
public product editProduct(@PathVariable String id, @RequestBody product newproduct) {
    product productToEdit = productRepo.findById(id).get();
    productToEdit.setName(newproduct.getName());
    productToEdit.setDescription(newproduct.getDescription());
    productToEdit.setCategory(newproduct.getCategory());
    productToEdit.setTags(newproduct.getTags()); // comma-separated string
    productToEdit.setPrice(newproduct.getPrice());
    productToEdit.setStock(newproduct.getStock());
return productRepo.save(productToEdit);
}
}