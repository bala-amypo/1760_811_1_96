package com.example.demo.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;



@Entity

@Table(name = "employee_profiles")
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String employeeId;
    private String fullname;
    @Email
    private String email;
    private String teamName;
    private String role;
    private Boolean active;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;
    
    @OneToOne(mappedBy = "emp")
    private UserAccount userAccount;

    // Many-to-many colleagues relationship (in-memory only for tests)
    @Transient
    private java.util.Set<EmployeeProfile> colleagues = new java.util.HashSet<>();
    public EmployeeProfile() {
    }
     @PrePersist
    public void onCreate() {
        if (active == null) {
            active = true;
        }
        if (createdAt == null) {
            createdAt = new Date(System.currentTimeMillis());
        }
    }
    public EmployeeProfile( String employeeId, String fullname, String email, String teamName, Boolean active,
            Date createdAt) {
        
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

    // Backwards-compatible name used by tests
    public String getFullName() { return getFullname(); }
    public void setFullName(String fullName) { setFullname(fullName); }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public java.util.Set<EmployeeProfile> getColleagues() { return colleagues; }
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
    public Boolean isActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}

