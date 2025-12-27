package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EmployeeProfile;

public interface EmployeeService {

    public EmployeeProfile create(EmployeeProfile emp);
    public List<EmployeeProfile> getByTeam(String team);
    public EmployeeProfile getById(Long id);
    public List<EmployeeProfile> getAll();
    public EmployeeProfile update(Long id, EmployeeProfile emp);
    public void deactivate(Long id);
}
