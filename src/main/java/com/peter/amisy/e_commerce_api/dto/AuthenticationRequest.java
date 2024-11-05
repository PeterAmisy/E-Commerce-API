package com.peter.amisy.e_commerce_api.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}