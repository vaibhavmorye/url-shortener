package com.urlshortner.service;

import com.urlshortner.dto.LoginResponse;
import com.urlshortner.dto.SignUpRequest;
import com.urlshortner.model.BaseRole;
import com.urlshortner.model.Role;
import com.urlshortner.model.User;
import com.urlshortner.repository.RoleRepository;
import com.urlshortner.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseEntity<LoginResponse> create(SignUpRequest sign) throws BadRequestException {
        if (userRepository.existsByUsername(sign.getUsername())) {
            throw new BadRequestException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(sign.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(BaseRole.ROLE_USER).get());
        // Create new user's account
        User user = new User(sign.getUsername(),
                sign.getEmail(),
                encoder.encode(sign.getPassword()), roles);


        userRepository.save(user);

        return ResponseEntity.ok().body(LoginResponse.builder()
                .status("Success")
                .roles(user.getRoles().stream().map(x -> x.getName().name()).collect(Collectors.toList()))
                .username(user.getUsername()).build());
    }


}
