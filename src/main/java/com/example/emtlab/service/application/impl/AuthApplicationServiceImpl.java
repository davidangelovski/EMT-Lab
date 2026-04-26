package com.example.emtlab.service.application.impl;

import com.example.emtlab.model.domain.AppUser;
import com.example.emtlab.model.dto.AuthRequestDto;
import com.example.emtlab.model.dto.AuthResponseDto;
import com.example.emtlab.model.enums.Role;
import com.example.emtlab.repository.AppUserRepository;
import com.example.emtlab.security.JwtService;
import com.example.emtlab.service.application.AuthApplicationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class AuthApplicationServiceImpl implements AuthApplicationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthApplicationServiceImpl(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDto.username(), authRequestDto.password())
            );
        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(UNAUTHORIZED, "Invalid username or password");
        }

        UserDetails user = appUserRepository.findByUsername(authRequestDto.username())
                .map(appUser -> User.builder()
                        .username(appUser.getUsername())
                        .password(appUser.getPassword())
                        .roles(appUser.getRole().name())
                        .build())
                .orElseThrow(() -> new ResponseStatusException(CONFLICT, "User not found after authentication"));

        String token = jwtService.generateToken(user);
        return new AuthResponseDto(token, jwtService.getJwtExpirationMs());
    }

    @Override
    public AuthResponseDto register(AuthRequestDto authRequestDto) {
        if (appUserRepository.existsByUsername(authRequestDto.username())) {
            throw new ResponseStatusException(CONFLICT, "Username already exists");
        }

        AppUser user = new AppUser(
                authRequestDto.username(),
                passwordEncoder.encode(authRequestDto.password()),
                Role.USER
        );

        appUserRepository.save(user);

        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);
        return new AuthResponseDto(token, jwtService.getJwtExpirationMs());
    }
}


