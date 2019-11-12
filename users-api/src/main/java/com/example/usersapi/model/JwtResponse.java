package com.example.usersapi.model;

public class JwtResponse {
    private String jwt;
    private String username;
    private int id;

    public JwtResponse(String jwt, String username, int id) {
        this.jwt = jwt;
        this.username = username;
        this.id = id;
    }

    public JwtResponse() { }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
