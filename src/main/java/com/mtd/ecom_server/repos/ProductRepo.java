package com.mtd.ecom_server.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mtd.ecom_server.models.product;

public interface ProductRepo extends MongoRepository<product,String>{

}