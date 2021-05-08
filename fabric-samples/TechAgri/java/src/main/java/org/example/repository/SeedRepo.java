package com.example.demo.repository;
import com.example.demo.domain.SeedInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface SeedRepo extends MongoRepository<SeedInfo,String>{

}
