package com.xport.shopify.service.interfaces;

import com.xport.shopify.dto.Login;
import com.xport.shopify.dto.LoginRS;

public interface IAuthService {
    Login getCredentials(String userEmail);
    LoginRS IsValidUser(Login login);
}
