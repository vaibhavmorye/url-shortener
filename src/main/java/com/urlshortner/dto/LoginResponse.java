package com.urlshortner.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {
    private String username;
    private String status;
    private List<String> roles;
}
