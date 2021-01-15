package com.restaurant.foodonline.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationUser {
    Authentication getAuthentication();
}


