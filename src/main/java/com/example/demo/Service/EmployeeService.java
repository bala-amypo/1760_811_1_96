package com.example.demo.Service;

import java.util.List;

import com.example.demo.model.EmployeeProfile;

public interface EmployeeService {

    public EmployeeProfile addEmployeeProfiles(EmployeeProfile emp);
    public List<EmployeeProfile> getByTeam(String team);
    public EmployeeProfile getById(Long id);
    public List<EmployeeProfile> getEmployeeProfiles();
    public EmployeeProfile updateEmployeeProfiles(Long id, EmployeeProfile emp);
    public void deleteEmployeeProfiles(Long id);
}
