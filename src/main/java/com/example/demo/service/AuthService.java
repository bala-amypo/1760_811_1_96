package com.example.demo.service;

import com.example.demo.model.UserAccount;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;

public interface AuthService {
    public String login(String username,String password);
    public UserAccount register(UserAccount account);
    public AuthResponse authenticate(AuthRequest req);
}
    