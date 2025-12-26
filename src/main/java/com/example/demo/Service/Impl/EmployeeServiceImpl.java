package com.example.demo.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.EmployeeService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeProfileRepository repo;

    @Override
    public EmployeeProfile create(EmployeeProfile emp) {
        emp.setId(null);
        return repo.save(emp);
    }

    @Override
    public List<EmployeeProfile> getByTeam(String team) {
        return repo.findByTeamName(team);
    }

    @Override
    public EmployeeProfile getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @Override
    public List<EmployeeProfile> getAll() {
        return repo.findAll();
    }

    @Override
    public EmployeeProfile update(Long id, EmployeeProfile emp) {
        EmployeeProfile existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (emp.getFullname() != null) existing.setFullname(emp.getFullname());
        if (emp.getTeamName() != null) existing.setTeamName(emp.getTeamName());
        if (emp.getRole() != null) existing.setRole(emp.getRole());
        return repo.save(existing);
    }

    @Override
    public void deactivate(Long id) {
        EmployeeProfile e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        e.setActive(false);
        repo.save(e);
    }
}
