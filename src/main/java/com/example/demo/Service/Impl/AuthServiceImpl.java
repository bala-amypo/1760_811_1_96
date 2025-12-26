package com.example.demo.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.AuthService;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.UserAccountRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AuthServiceImpl implements AuthService{
        @Autowired UserAccountRepository uar;
        @Autowired EmployeeProfileRepository emRepo;
        @Override
        public String login(String username, String password) {
                UserAccount user=uar.findByUsername(username);
                if(user.getPassword().equals(password)){
                      return "Login Successful";  
                }
                return "Login UnSuccessful";
        }
        @Override
        public UserAccount register(UserAccount account){
              
                 if (account.getEmp() != null && account.getEmp().getId() != null) {

                        Long empId = account.getEmp().getId();

                        EmployeeProfile emp = emRepo
                                .findById(empId)
                                .orElseThrow(() ->
                                        new RuntimeException("EmployeeProfile not found with id: " + empId));

                      
                        account.setEmp(emp); 
    }
                return uar.save(account);
        }
    
}