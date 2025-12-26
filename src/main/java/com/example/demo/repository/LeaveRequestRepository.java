package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LeaveRequest;
import com.example.demo.model.EmployeeProfile;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepository  extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(EmployeeProfile employee);

    @org.springframework.data.jpa.repository.Query("SELECT l FROM LeaveRequest l WHERE l.status = 'APPROVED' AND l.employee.teamName = :team AND l.startDate <= :end AND l.endDate >= :start")
    List<LeaveRequest> findApprovedOverlappingForTeam(@org.springframework.data.repository.query.Param("team") String team, @org.springframework.data.repository.query.Param("start") LocalDate start, @org.springframework.data.repository.query.Param("end") LocalDate end);

    @org.springframework.data.jpa.repository.Query("SELECT l FROM LeaveRequest l WHERE l.status = 'APPROVED' AND :date BETWEEN l.startDate AND l.endDate")
    List<LeaveRequest> findApprovedOnDate(@org.springframework.data.repository.query.Param("date") LocalDate date);
}
