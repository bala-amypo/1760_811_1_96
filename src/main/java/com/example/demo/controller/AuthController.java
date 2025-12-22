package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AuthService;
import com.example.demo.model.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService ser;

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount account) {
        return ser.register(account);
    }

     @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return ser.login(username, password);
    }
    
}
