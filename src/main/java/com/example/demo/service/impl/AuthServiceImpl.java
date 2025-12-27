package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.AuthService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AuthServiceImpl implements AuthService{
        @Autowired UserAccountRepository uar;
        @Autowired EmployeeProfileRepository emRepo;

        // New constructor used by tests
        public AuthServiceImpl() {}

        private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder;
        private com.example.demo.security.JwtTokenProvider tokenProvider;

        public AuthServiceImpl(UserAccountRepository userRepo, org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder, com.example.demo.security.JwtTokenProvider tokenProvider) {
            this.uar = userRepo;
            this.encoder = encoder;
            this.tokenProvider = tokenProvider;
        }

        @Override
        public String login(String username, String password) {
                UserAccount user=uar.findByUsername(username);
                if(user.getPassword().equals(password)){
                      return "Login Successful";  
                }
                return "Login UnSuccessful";
        }
       @Override
public UserAccount register(UserAccount account) {

    
    if (account.getEmp() == null || account.getEmp().getId() == null) {
        throw new RuntimeException("Employee ID is required");
    }

    Long empId = account.getEmp().getId();

   
    EmployeeProfile emp = emRepo.findById(empId)
            .orElseThrow(() -> new RuntimeException(
                    "EmployeeProfile not found with id: " + empId));

   
   
    account.setEmp(emp);

    
    return uar.save(account);
}

        // New method expected by tests
        public com.example.demo.dto.AuthResponse authenticate(com.example.demo.dto.AuthRequest req) {
            java.util.Optional<UserAccount> opt = uar.findByEmail(req.getEmail());
            if (opt == null || opt.isEmpty()) throw new com.example.demo.exception.BadRequestException("User not found");
            UserAccount user = opt.get();
            if (!this.encoder.matches(req.getPassword(), user.getPassword())) throw new com.example.demo.exception.BadRequestException("Invalid credentials");
            String token = this.tokenProvider.generateToken(user);
            return new com.example.demo.dto.AuthResponse(token, user.getId());
        }
    
}