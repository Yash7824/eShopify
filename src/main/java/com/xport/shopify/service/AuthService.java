package com.xport.shopify.service;

import com.xport.shopify.dto.Login;
import com.xport.shopify.dto.LoginRS;
import com.xport.shopify.repository.AuthRepository;
import com.xport.shopify.service.interfaces.IAuthService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements IAuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public Login getCredentials(String userEmail) {
        Login login;
        try{
            login = authRepository.getCredentials(userEmail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return login;
    }

    @Override
    public LoginRS IsValidUser(Login login) {
        LoginRS loginRS = new LoginRS();
        try{
            Login existingUserLogin = getCredentials(login.getUserEmail());
            if(Objects.equals(existingUserLogin.getUserEmail(), login.getUserEmail())
                    && Objects.equals(existingUserLogin.getPassword(), login.getPassword())){
                loginRS.setStatus(true);
                loginRS.setStatusMessage("Credentials matched");
            }else{
                loginRS.setStatus(false);
                loginRS.setStatusMessage("Credentials not matched");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return loginRS;
    }
}
