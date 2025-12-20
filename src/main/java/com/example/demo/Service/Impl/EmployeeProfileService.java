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
    public EmployeeProfile create(EmployeeProfile emp) {
        
        emp.setId(null);
        return repo.save(emp);
    }


    @Override
    public List<EmployeeProfile> getAll() {
        return repo.findAll();
    }
    @Override
    public List<EmployeeProfile> getByTeam(String team) { 
        return repo.findByTeamName(team);
    }
    @Override
    public EmployeeProfile update(Long id,EmployeeProfile emp) { 
        emp.setId(id);
        return repo.save(emp);
    }
    @Override
    public EmployeeProfile getById(Long id) {   
        return repo.findById(id).orElse(null);
    }
    @Override
    public void deactivate(Long id) {
         EmployeeProfile emp = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        emp.setActive(false);
        repo.save(emp);

    }
}
