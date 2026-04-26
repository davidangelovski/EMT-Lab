package com.example.emtlab.config;

import com.example.emtlab.model.domain.AppUser;
import com.example.emtlab.model.enums.Role;
import com.example.emtlab.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthBootstrapConfig {

    @Bean
    public CommandLineRunner seedAuthUser(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.auth.seed.enabled:true}") boolean seedEnabled,
            @Value("${app.auth.seed.username:admin}") String seedUsername,
            @Value("${app.auth.seed.password:admin123}") String seedPassword
    ) {
        return args -> {
            if (!seedEnabled || appUserRepository.existsByUsername(seedUsername)) {
                return;
            }

            AppUser admin = new AppUser(seedUsername, passwordEncoder.encode(seedPassword), Role.ADMIN);
            appUserRepository.save(admin);
        };
    }
}

