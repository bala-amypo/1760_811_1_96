package com.example.demo.Service.Impl;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LeaveRequestServiceImpl implements com.example.demo.Service.LeaveRequestService {
    private final LeaveRequestRepository leaveRepo;
    private final EmployeeProfileRepository employeeRepo;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRepo, EmployeeProfileRepository employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }

    public LeaveRequestDto create(LeaveRequestDto dto) {
        EmployeeProfile emp = employeeRepo.findById(dto.getEmployeeId()).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (dto.getStartDate().isAfter(dto.getEndDate())) throw new BadRequestException("Invalid date range");
        LeaveRequest lr = new LeaveRequest();
        lr.setEmployee(emp);
        lr.setStartDate(dto.getStartDate());
        lr.setEndDate(dto.getEndDate());
        lr.setType(dto.getType());
        lr.setReason(dto.getReason());
        lr.setStatus("PENDING");
        LeaveRequest saved = leaveRepo.save(lr);
        LeaveRequestDto out = new LeaveRequestDto();
        out.setId(saved.getId());
        out.setStatus(saved.getStatus());
        return out;
    }

    public LeaveRequestDto approve(Long id) {
        LeaveRequest lr = leaveRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        lr.setStatus("APPROVED");
        LeaveRequest saved = leaveRepo.save(lr);
        LeaveRequestDto out = new LeaveRequestDto();
        out.setId(saved.getId());
        out.setStatus(saved.getStatus());
        return out;
    }

    public LeaveRequestDto reject(Long id) {
        LeaveRequest lr = leaveRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
        lr.setStatus("REJECTED");
        LeaveRequest saved = leaveRepo.save(lr);
        LeaveRequestDto out = new LeaveRequestDto();
        out.setId(saved.getId());
        out.setStatus(saved.getStatus());
        return out;
    }

    public List<LeaveRequestDto> getByEmployee(Long empId) {
        EmployeeProfile emp = employeeRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return leaveRepo.findByEmployee(emp).stream().map(l -> {
            LeaveRequestDto dto = new LeaveRequestDto();
            dto.setId(l.getId());
            dto.setStatus(l.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<LeaveRequestDto> getOverlappingForTeam(String team, LocalDate start, LocalDate end) {
        return leaveRepo.findApprovedOverlappingForTeam(team, start, end).stream().map(l -> {
            LeaveRequestDto dto = new LeaveRequestDto();
            dto.setId(l.getId());
            dto.setStatus(l.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }
}
