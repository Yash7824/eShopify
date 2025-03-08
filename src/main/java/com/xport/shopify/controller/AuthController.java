package com.xport.shopify.controller;

import com.xport.shopify.dto.Login;
import com.xport.shopify.dto.LoginRS;
import com.xport.shopify.dto.TokenReq;
import com.xport.shopify.service.AuthService;
import com.xport.shopify.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService){
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRS> login(@RequestBody Login login){
        LoginRS loginRS = authService.IsValidUser(login);

        if(loginRS.isStatus()){
            TokenReq tokenReq = new TokenReq(null, login.getUserEmail());
            loginRS.setToken(jwtService.generateToken(tokenReq));
        }else{
            loginRS.setToken("");
        }

        return ResponseEntity.ok(loginRS);
    }
}
