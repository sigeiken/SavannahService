package com.savannahinformatics.SavannahService.response;

import java.time.Instant;

public class LoginResponse {

    private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private String username;

    public LoginResponse() {
    }

    public LoginResponse(String authenticationToken, String refreshToken, Instant expiresAt, String username) {
        this.authenticationToken = authenticationToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
        this.username = username;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "authenticationToken='" + authenticationToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresAt=" + expiresAt +
                ", username='" + username + '\'' +
                '}';
    }
}
