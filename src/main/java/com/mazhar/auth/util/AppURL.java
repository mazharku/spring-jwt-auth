package com.mazhar.auth.util;

public class AppURL {

    public static final String base_url = "/api/welcome/";
    public static final String[] AUTH_URL = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            base_url+"authenticate"
    };
}
