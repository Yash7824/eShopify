package com.xport.shopify.service;

import com.xport.shopify.dto.TokenReq;
import com.xport.shopify.dto.UserDto;
import com.xport.shopify.service.interfaces.IJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JwtService implements IJwtService {

    private final UserService userService;

    private final String secretKey;
    public JwtService(UserService userService){
        this.userService = userService;
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        }catch(NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    public String generateToken(TokenReq tokenReq) {
        String token = "";
        try{
            Map<String, Object> claims = new HashMap<>();
            UserDto userDto = userService.getUserByEmail(tokenReq.getUserEmail());
            claims.put("userId", userDto.getUserId());
            claims.put("userName", userDto.getUserEmail());
            token = Jwts.builder()
                    .claims()
                    .add(claims)
                    .subject(userDto.getUserEmail())
                    .issuer(userDto.getUserEmail())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                    .and()
                    .signWith(getKey())
                    .compact();
        }catch(Exception ex){
            System.out.println("Exception occurred: " + ex.getMessage());
        }
        return token;
    }

    @Override
    public Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
