package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {
    @Autowired
    EmployeeService ser;

    @PostMapping("/create")
    public EmployeeProfile create(@RequestBody EmployeeProfile entity) {
        

        return ser.create(entity);
    }

    @GetMapping("/getAll")
    public List<EmployeeProfile> getAll() {
        return ser.getAll();
    }

    @GetMapping("/get/{id}")
     public EmployeeProfile getById(@PathVariable Long id) {
        return ser.getById(id);
    }
    
   @PutMapping("/update/{id}")
public EmployeeProfile update(
        @PathVariable Long id,
        @RequestBody EmployeeProfile entity) {
    return ser.update(id, entity);
}

    @DeleteMapping("/deactivate")
    public String deactivate(@RequestParam Long id) {
      ser.deactivate(id);
      return "Employee with"+id+" deactivated successfully";
    }
    @GetMapping("/getByTeam")
    public List<EmployeeProfile> getByTeam(@RequestParam String team) { 
        return ser.getByTeam(team);
    }
    
}
