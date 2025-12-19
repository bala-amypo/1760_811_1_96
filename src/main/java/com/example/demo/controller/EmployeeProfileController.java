package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.EmployeeService;
import com.example.demo.model.EmployeeProfile;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
public class EmployeeProfileController {
    @Autowired
    EmployeeService ser;

    @PostMapping("/add")
    public EmployeeProfile addEmployeeProfile(@RequestBody EmployeeProfile entity) {
        

        return ser.addEmployeeProfiles(entity);
    }

    @GetMapping("/get")
    public List<EmployeeProfile> getEmployeeProfiles() {
        return ser.getEmployeeProfiles();
    }
    
    
}
