package com.urlshortner.dto;

import lombok.Builder;
import lombok.Data;

/**  Roles need to be set by administrator and admin login will be crated at database
 * end directly by default only user role will be allowed
 * from api admin will change role by different api assisible to only admin
 *  **/
@Data
@Builder
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
}