package com.example.demo.model;



import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CapacityAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    
    private Long id;
    private LocalDate date;
    private String teamName;
    private String severity;
    private String message;
    public CapacityAlert() {
    }
    public CapacityAlert(LocalDate date, String teamName, String severity, String message) {
        this.date = date;
        this.teamName = teamName;
        this.severity = severity;
        this.message = message;
    }
    // Convenience constructor that matches older tests using (team, date, severity, message)
    public CapacityAlert(String teamName, LocalDate date, String severity, String message) {
        this(date, teamName, severity, message);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}