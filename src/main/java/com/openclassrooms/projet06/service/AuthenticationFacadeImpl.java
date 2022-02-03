package com.openclassrooms.projet06.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacadeImpl {
    Authentication getAuthentication();
}
