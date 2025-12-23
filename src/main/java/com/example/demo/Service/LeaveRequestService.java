package com.example.demo.Service;

import com.example.demo.model.LeaveRequest;
// import java.util.*;
public interface LeaveRequestService {
    public LeaveRequest create(LeaveRequest lq);
    public void approve(Long id);
    public void reject(Long id);
    // public LeaveRequest getByEmployee(String empid);
    public List<LeaveRequest> getLeaveByEmployee(int id);
}
