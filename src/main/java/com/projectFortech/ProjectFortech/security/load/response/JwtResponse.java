package com.projectFortech.ProjectFortech.security.load.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String name;
    private String surname;
    private String email;
    private String role;

    public JwtResponse(String accessToken, int id, String name,String surname, String email, String role) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.surname= surname;
        this.email = email;
        this.role = role;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }


}
