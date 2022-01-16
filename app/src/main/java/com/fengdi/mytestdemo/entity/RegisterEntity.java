package com.fengdi.mytestdemo.entity;

public class RegisterEntity {
    public String uid;
    public String token;
    public String username;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "RegisterEntity{" +
                "uid='" + uid + '\'' +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
