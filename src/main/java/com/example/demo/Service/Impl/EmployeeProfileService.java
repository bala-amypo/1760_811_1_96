package com.example.demo.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.EmployeeService;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;

@Service
public class EmployeeProfileService implements EmployeeService {

    @Autowired
    private EmployeeProfileRepository repo;

    @Override
    public EmployeeProfile addEmployeeProfiles(EmployeeProfile emp) {
        return repo.save(emp);
    }

    @Override
    public List<EmployeeProfile> getEmployeeProfiles() {
        return repo.findAll();
    }
}
