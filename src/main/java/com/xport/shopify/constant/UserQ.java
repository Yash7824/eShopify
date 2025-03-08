package com.xport.shopify.constant;

public class UserQ {
    public static final String InsertUserQ = "INSERT INTO Users (user_id, user_name, user_email, user_password, user_mobile, created_at) VALUES (:id, :name, :email, :password, :phone, :createdAt)";
    public static final String GetUserQ = "SELECT user_id, user_name, user_email, user_password, user_mobile, created_at FROM Users where user_id = :user_id";
    public static final String GetUsersQ = "SELECT * FROM users";
    public static final String UpdateUserQ = "UPDATE users set user_name = :user_name, user_email = :user_email, user_password = :user_password, user_mobile = :user_mobile where user_id = :user_id";
    public static final String DeleteUserQ = "DELETE FROM users where user_id = :user_id";
}
