package com.xport.shopify.service.interfaces;

import com.xport.shopify.dto.TokenReq;

import java.security.Key;

public interface IJwtService {
    String generateToken(TokenReq tokenReq);
    Key getKey();
}
