package com.infosys.authservice.Controller;

import com.infosys.authservice.Entity.User;
import com.infosys.authservice.Repository.UserRepo;
import com.infosys.authservice.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private UserRepo userRepo;
    @Autowired private TokenService tokenService;
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) { userRepo.save(user); return "User registered"; }
    @PostMapping("/token")
    public String getToken(@RequestBody User user) { return tokenService.generateToken(user.getUsername()); }
}
