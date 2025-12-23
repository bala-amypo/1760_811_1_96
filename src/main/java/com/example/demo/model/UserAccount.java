package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class UserAccount {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@JsonProperty(access = JsonProperty.Access.READ_ONLY)
private Long id;
private String username;
@Column(unique=true)
private String email;
private String password;
private String role;
@OneToOne
@JoinColumn(name = "employee_profile_id")
private EmployeeProfile emp;

public UserAccount() {
}

public UserAccount(Long id,String username, String email, String password, String role, EmployeeProfile emp) {
    this.id=id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.emp = emp;
}

public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
}
public String getRole() {
    return role;
}
public void setRole(String role) {
    this.role = role;
}
public EmployeeProfile getEmp() {
    return emp;
}
public void setEmp(EmployeeProfile emp) {
    this.emp = emp;
}


}
