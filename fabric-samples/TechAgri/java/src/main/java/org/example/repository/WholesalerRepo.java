/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.domain.Wholesaler;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.websocket.MessageHandler;

/**
 *
 * @author didin
 */
public interface WholesalerRepo extends MongoRepository<Wholesaler, String> {

    Wholesaler findByEmail(String email);

}
