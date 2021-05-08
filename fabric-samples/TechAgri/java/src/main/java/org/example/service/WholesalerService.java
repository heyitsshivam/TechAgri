/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.Wholesaler;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.WholesalerRepo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author didin
 */
@Service
public class WholesalerService implements UserDetailsService {

    @Autowired
    private WholesalerRepo wholesalerRepo;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Wholesaler findUserByEmail(String email) {
        return wholesalerRepo.findByEmail(email);
    }

    public void saveUser(Wholesaler wholesaler) {
        wholesaler.setPassword(bCryptPasswordEncoder.encode(wholesaler.getPassword()));
        wholesaler.setEnabled(true);
        Role userRole = roleRepository.findByRole("WHOLEUSER");
        wholesaler.setRoles(new HashSet<>(Arrays.asList(userRole)));
        wholesalerRepo.save(wholesaler);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Wholesaler wholesaler = wholesalerRepo.findByEmail(email);
        if(wholesaler != null) {
            List<GrantedAuthority> authorities = getUserAuthority(wholesaler.getRoles());
            return buildUserForAuthentication(wholesaler, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Wholesaler wholesaler, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(wholesaler.getEmail(), wholesaler.getPassword(), authorities);
    }

}
