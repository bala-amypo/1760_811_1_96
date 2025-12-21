package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TeamCapacityRule;

@Repository
public interface TeamCapacityConfigRepository extends JpaRepository<TeamCapacityRule, Long> {
    TeamCapacityRule findByTeamName(String teamName);
}
