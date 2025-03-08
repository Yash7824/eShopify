package com.xport.shopify.repository;

import com.xport.shopify.constant.LoginQ;
import com.xport.shopify.dto.Login;
import com.xport.shopify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AuthRepository extends JpaRepository<User, String> {

    @Transactional
    @Query(value = LoginQ.GetLoginQ, nativeQuery = true)
    Login getCredentials(@Param("user_email") String userEmail);
}
