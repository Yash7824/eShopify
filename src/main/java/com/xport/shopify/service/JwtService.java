package com.xport.shopify.service;

import com.xport.shopify.service.interfaces.IJwtService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService implements IJwtService {

    @Override
    public String generateToken(UUID userId) {
        return "";
    }
}
