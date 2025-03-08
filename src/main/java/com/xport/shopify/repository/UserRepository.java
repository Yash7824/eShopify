package com.xport.shopify.repository;

import com.xport.shopify.constant.UserQ;
import com.xport.shopify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Modifying
    @Transactional
    @Query(value = UserQ.InsertUserQ, nativeQuery = true)
    void insertUser(@Param("id") UUID id,
                    @Param("name") String name,
                    @Param("email") String email,
                    @Param("password") String password,
                    @Param("phone") String phone,
                    @Param("createdAt") LocalDateTime createdAt);

    @Transactional
    @Query(value = UserQ.GetUserQ, nativeQuery = true)
    Map<String, Object> getUserQ(@Param("user_id") UUID user_id);

    @Transactional
    @Query(value = UserQ.GetUsersQ, nativeQuery = true)
    List<Map<String, Object>> getUsersQ();

    @Modifying
    @Transactional
    @Query(value = UserQ.UpdateUserQ, nativeQuery = true)
    int updateUserQ(@Param("user_name") String user_name,
                                    @Param("user_email") String user_email,
                                    @Param("user_password") String user_password,
                                    @Param("user_mobile") String user_mobile,
                                    @Param("user_id") UUID user_id);

    @Modifying
    @Transactional
    @Query(value = UserQ.DeleteUserQ, nativeQuery = true)
    int deleteUserQ(@Param("user_id") UUID userId);
}
