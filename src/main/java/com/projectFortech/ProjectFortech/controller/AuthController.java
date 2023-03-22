package com.projectFortech.ProjectFortech.controller;

import com.projectFortech.ProjectFortech.domain.Client;
import com.projectFortech.ProjectFortech.domain.User;
import com.projectFortech.ProjectFortech.repository.UserRepository;
import com.projectFortech.ProjectFortech.security.jwt.JwtUtils;
import com.projectFortech.ProjectFortech.security.load.request.LoginRequest;
import com.projectFortech.ProjectFortech.security.load.request.SignUpRequest;
import com.projectFortech.ProjectFortech.security.load.response.JwtResponse;
import com.projectFortech.ProjectFortech.security.load.response.MessageResponse;
import com.projectFortech.ProjectFortech.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Objects;

import static com.projectFortech.ProjectFortech.enums.Role.CLIENT;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        if(!userRepository.existsByEmail(loginRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is not found!"));
        }
        if(userRepository.findByEmail(loginRequest.getEmail()).isPresent()){
            if(!encoder.matches(loginRequest.getPassword(), userRepository.findByEmail(loginRequest.getEmail()).get().getPassword()))
                return ResponseEntity
                        .badRequest().body(new MessageResponse("Error: Password is not correct!"));}

           Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getSurname(), userDetails.getEmail(), role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        // Create new user's account
        Client user = new Client(signUpRequest.getName(),signUpRequest.getSurname(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
