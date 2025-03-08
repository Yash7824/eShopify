package com.xport.shopify.constant;

public class LoginQ {
    public static final String GetLoginQ = "SELECT user_email, user_password from users where user_email = :user_email";
}
