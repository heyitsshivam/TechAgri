package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.domain.ContactForm;

public interface ContactformRepository extends CrudRepository< ContactForm , String > {
}
