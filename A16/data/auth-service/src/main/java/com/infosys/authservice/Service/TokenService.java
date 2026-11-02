package com.infosys.authservice.Service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generateToken(String username) { return "sample_token_" + username; }
}
