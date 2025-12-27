package com.example.demo.service.impl;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.service.LeaveRequestService;
import com.example.demo.repository.EmployeeProfileRepository;
@Service
public class LeaveRequestImpl implements LeaveRequestService{
    @Autowired
    LeaveRequestRepository repo;

    @Autowired
    EmployeeProfileRepository emprepo;

    @Override
    public com.example.demo.dto.LeaveRequestDto create(com.example.demo.dto.LeaveRequestDto dto) {
        // Map DTO to entity
        EmployeeProfile emp = emprepo.findById(dto.getEmployeeId())
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + dto.getEmployeeId()));
        if (dto.getStartDate().isAfter(dto.getEndDate())) throw new com.example.demo.exception.BadRequestException("Invalid date range");
        LeaveRequest lq = new LeaveRequest();
        lq.setEmployee(emp);
        lq.setStartDate(dto.getStartDate());
        lq.setEndDate(dto.getEndDate());
        lq.setType(dto.getType());
        lq.setReason(dto.getReason());
        lq.setStatus("PENDING");
        LeaveRequest saved = repo.save(lq);
        com.example.demo.dto.LeaveRequestDto out = new com.example.demo.dto.LeaveRequestDto();
        out.setId(saved.getId());
        out.setStatus(saved.getStatus());
        return out;
    }
     @Override
    public com.example.demo.dto.LeaveRequestDto approve(Long id) {
         LeaveRequest lrq = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        lrq.setStatus("APPROVED");
        LeaveRequest saved = repo.save(lrq);
        com.example.demo.dto.LeaveRequestDto out = new com.example.demo.dto.LeaveRequestDto();
        out.setId(saved.getId());
        out.setStatus(saved.getStatus());
        return out;
    }
     @Override
    public com.example.demo.dto.LeaveRequestDto reject(Long id) {
        LeaveRequest lrq = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        lrq.setStatus("REJECTED");
        LeaveRequest saved = repo.save(lrq);
        com.example.demo.dto.LeaveRequestDto out = new com.example.demo.dto.LeaveRequestDto();
        out.setId(saved.getId());
        out.setStatus(saved.getStatus());
        return out;
    }

    @Override
    public java.util.List<com.example.demo.dto.LeaveRequestDto> getByEmployee(Long empId) {
        EmployeeProfile emp = emprepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return repo.findByEmployee(emp).stream().map(l -> {
            com.example.demo.dto.LeaveRequestDto dto = new com.example.demo.dto.LeaveRequestDto();
            dto.setId(l.getId());
            dto.setStatus(l.getStatus());
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public java.util.List<com.example.demo.dto.LeaveRequestDto> getOverlappingForTeam(String team, java.time.LocalDate start, java.time.LocalDate end) {
        return repo.findApprovedOverlappingForTeam(team, start, end).stream().map(l -> {
            com.example.demo.dto.LeaveRequestDto dto = new com.example.demo.dto.LeaveRequestDto();
            dto.setId(l.getId());
            dto.setStatus(l.getStatus());
            return dto;
        }).collect(java.util.stream.Collectors.toList());
    }
    //  @Override
    // public LeaveRequest getByEmployee(String empid) {
    //    return emprepo.findByemployeeId(empid);
    // }
    

    //  @Override
    // public List<LeaveRequest> getByOverlappingForTeam(LeaveRequest lq) {
    //    return [lq];
    // }
}
