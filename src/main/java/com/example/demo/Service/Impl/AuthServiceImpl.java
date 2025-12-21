package com.example.demo.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.AuthService;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;

@Service
public class AuthServiceImpl implements AuthService{
        @Autowired UserAccountRepository uar;

        @Override
        public String login(String username, String password) {
                
                return "Login Successful";
        }
        @Override
        public UserAccount register(UserAccount account){
              
                return uar.save(account);
        }
    
}