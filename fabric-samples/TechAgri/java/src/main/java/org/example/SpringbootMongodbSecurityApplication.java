package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.example.demo.repository")
public class SpringbootMongodbSecurityApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbSecurityApplication.class, args);
    }


    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {

        return args -> {

            Role adminRole = roleRepository.findByRole("ADMIN");
            if (adminRole == null) {
                Role newAdminRole = new Role();
                newAdminRole.setRole("ADMIN");
                roleRepository.save(newAdminRole);
            }
            
            Role userRole = roleRepository.findByRole("USER");
            if (userRole == null) {
                Role newUserRole = new Role();
                newUserRole.setRole("USER");
                roleRepository.save(newUserRole);
            }
            Role wholeuserRole = roleRepository.findByRole("WHOLEUSER");
            if (wholeuserRole == null) {
                Role newwholeuserRole = new Role();
                newwholeuserRole.setRole("WHOLEUSER");
                roleRepository.save(newwholeuserRole);
            }

        };

    }
}
