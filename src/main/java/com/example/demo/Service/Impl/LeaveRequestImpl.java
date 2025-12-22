package com.example.demo.Service.Impl;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.LeaveRequestService;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.exception.ResourceNotFoundException;
@Service
public class LeaveRequestImpl implements LeaveRequestService{
    @Autowired
    LeaveRequestRepository repo;

    @Autowired
    EmployeeProfileRepository emprepo;

    @Override
    public LeaveRequest create(LeaveRequest lq) {
        // If client provided employeeId use it to fetch EmployeeProfile
        if (lq.getEmployeeId() != null) {
            EmployeeProfile emp = emprepo.findById(lq.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + lq.getEmployeeId()));
            lq.setEmployee(emp);
        } else if (lq.getEmployee() == null) {
            throw new ResourceNotFoundException("Employee information missing");
        }
        if (lq.getStatus() == null) {
            lq.setStatus("PENDING");
        }
       return repo.save(lq);
    }
     @Override
    public void approve(Long id) {
         LeaveRequest lrq = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        lrq.setStatus("APPROVED");
        
        repo.save(lrq);
    }
     @Override
    public void reject(Long id) {
        LeaveRequest lrq = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        lrq.setStatus("REJECTED");
        repo.save(lrq);
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
