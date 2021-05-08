package com.example.demo.repository;

import com.example.demo.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository< Product , String> {
}
