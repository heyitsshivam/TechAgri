package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.domain.Feeduser;

public interface FeedbackRepository extends CrudRepository< Feeduser , String>{
}
