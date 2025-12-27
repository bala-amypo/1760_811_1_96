package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.TeamCapacityService;

@Service
public class TeamCapacityServiceImpl implements TeamCapacityService {
    @Autowired UserAccountRepository uar;
    @Override
    public String analyze(String teamName,java.sql.Date startDate,java.sql.Date endDate){
        // Implementation logic here
        return "Analysis result for team: " + teamName+", from "+startDate+" to "+endDate+" completed.";
    }
    @Override
    public String getAlert(String teamName){
        // Implementation logic here
        return "Your team: " + teamName+" has low capacity workers so leave request has been cancelled.";
    }
}
