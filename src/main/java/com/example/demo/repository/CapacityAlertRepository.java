package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CapacityAlert;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CapacityAlertRepository extends JpaRepository<CapacityAlert,Long> {
    List<CapacityAlert> findByTeamNameAndDateBetween(String teamName, LocalDate start, LocalDate end);
}
