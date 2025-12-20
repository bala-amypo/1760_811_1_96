package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.EmployeeService;
import com.example.demo.model.EmployeeProfile;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





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

    @GetMapping("/get/{id}")
     public EmployeeProfile getById(@PathVariable Long id) {
        return ser.getById(id);
    }
    
   @PutMapping("/update/{id}")
public EmployeeProfile updateEmployeeProfile(
        @PathVariable Long id,
        @RequestBody EmployeeProfile entity) {
    return ser.updateEmployeeProfiles(id, entity);
}

    @DeleteMapping("/del")
    public void deleteEmployeeProfile(@RequestParam Long id) {
      ser.deleteEmployeeProfiles(id);
    }
    @GetMapping("/getByTeam")
    public List<EmployeeProfile> getByTeam(@RequestParam String team) { 
        return ser.getByTeam(team);
    }
    
}
