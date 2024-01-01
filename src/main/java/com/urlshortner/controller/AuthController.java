package com.urlshortner.controller;

import com.urlshortner.dto.LoginRequest;
import com.urlshortner.dto.LoginResponse;
import com.urlshortner.dto.SignUpRequest;
import com.urlshortner.security.jwt.JwtUtils;
import com.urlshortner.security.service.AuthService;
import com.urlshortner.security.service.UserDetailsImp;
import com.urlshortner.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/v1/auth/public/")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("signup")
    private ResponseEntity<LoginResponse> signup(@RequestBody SignUpRequest signUpRequest) throws BadRequestException {
        return userService.create(signUpRequest);
    }

    @PostMapping("login")
    private ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetailsImp authenticate = authService.authenticate(loginRequest);
        ResponseCookie jwt = jwtUtils.generateJwtCookie(authenticate);
        // List<String> roles = authenticate.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwt.toString()).body(LoginResponse.builder()
                .username(authenticate.getUsername())
                .status("Success")
                .roles(null).build());
    }
}


