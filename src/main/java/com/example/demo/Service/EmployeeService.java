package com.example.demo.Service;

import java.util.List;

import com.example.demo.model.EmployeeProfile;

public interface EmployeeService {

    public EmployeeProfile addEmployeeProfiles(EmployeeProfile emp);
    public List<EmployeeProfile> getEmployeeProfiles();
    
}
