package com.xport.shopify.service.interfaces;

import java.util.UUID;

public interface IJwtService {
    public String generateToken(UUID userId);
}
