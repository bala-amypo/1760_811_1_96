package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "team_capacity_rules")
public class TeamCapacityRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column()
    private Long id;

    @Column(unique=true,nullable = false)
    private String teamName;
    private int totalHeadcount;
    private int minCapacityPercent;
    public TeamCapacityRule() {
    }
    public TeamCapacityRule(String teamName, int totalHeadcount, int minCapacityPercent) {
        this.teamName = teamName;
        this.totalHeadcount = totalHeadcount;
        this.minCapacityPercent = minCapacityPercent;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public int getTotalHeadcount() {
        return totalHeadcount;
    }
    public void setTotalHeadcount(int totalHeadcount) {
        this.totalHeadcount = totalHeadcount;
    }
    public int getMinCapacityPercent() {
        return minCapacityPercent;
    }
    public void setMinCapacityPercent(int minCapacityPercent) {
        this.minCapacityPercent = minCapacityPercent;
    }
    



}
