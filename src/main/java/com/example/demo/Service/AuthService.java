package com.example.demo.Service;

import com.example.demo.model.UserAccount;

public interface AuthService {
    public String login(String username,String password);
    public UserAccount register(UserAccount account);
    
}
