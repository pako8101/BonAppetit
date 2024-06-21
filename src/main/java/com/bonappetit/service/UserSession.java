package com.bonappetit.service;

import com.bonappetit.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class UserSession {

    private String username;
    private long id;

    public void login(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public UserSession() {
    }

    public String getUsername() {
        return username;
    }

    public UserSession setUsername(String username) {
        this.username = username;
        return this;
    }

    public long getId() {
        return id;
    }

    public UserSession setId(long id) {
        this.id = id;
        return this;
    }
    public void logout(){
        id = 0;
        username = "";
    }
    public boolean isLoggedIn(){
        return id != 0;
    }
}
