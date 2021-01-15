package com.restaurant.foodonline.security;


import com.restaurant.foodonline.config.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 86400000; // 1 day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_CUSTOMER_URL = "/customer/register";
    public static final String SIGN_UP_RESTAURANT_URL = "/restaurant/register";
    public static final String USER_LOGIN_URL = "/user/login";

    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
