package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile {
    @Id

    private Long id;
    private String employeeId;
    private String fullname;
  
    private String email;
    private String teamName;
    private boolean active;
    private Date createdAt;
    
    public EmployeeProfile() {
    }
    public EmployeeProfile(Long id, String employeeId, String fullname, String email, String teamName, boolean active,
            Date createdAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.fullname = fullname;
        this.email = email;
        this.teamName = teamName;
        this.active = active;
        this.createdAt = createdAt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}

