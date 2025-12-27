package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.model.CapacityAlert;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.util.DateRangeUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
//import java.util.stream.Collectors;

public class CapacityAnalysisServiceImpl implements com.example.demo.Service.CapacityAnalysisService {
    private final TeamCapacityConfigRepository configRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final LeaveRequestRepository leaveRepo;
    private final CapacityAlertRepository alertRepo;

    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository configRepo, EmployeeProfileRepository employeeRepo, LeaveRequestRepository leaveRepo, CapacityAlertRepository alertRepo) {
        this.configRepo = configRepo;
        this.employeeRepo = employeeRepo;
        this.leaveRepo = leaveRepo;
        this.alertRepo = alertRepo;
    }

    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        TeamCapacityConfig cfg = configRepo.findByTeamName(teamName).orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));
        if (start.isAfter(end)) throw new BadRequestException("Start date must be before end date");
        if (cfg.getTotalHeadcount() <= 0) throw new BadRequestException("Invalid total headcount");

        List<LocalDate> dates = DateRangeUtil.daysBetween(start, end);
        List<?> leaves = leaveRepo.findApprovedOverlappingForTeam(teamName, start, end);
        int leavesCount = leaves.size();

        Map<LocalDate,Integer> capacityByDate = new HashMap<>();
        for (LocalDate d: dates) {
            int capacityPercent = (int)(((double)(cfg.getTotalHeadcount() - leavesCount) / cfg.getTotalHeadcount()) * 100);
            capacityByDate.put(d, capacityPercent);
        }

        boolean risky = capacityByDate.values().stream().anyMatch(v -> v < cfg.getMinCapacityPercent());
        if (risky) {
            // create an alert for the first date
            CapacityAlert alert = new CapacityAlert(teamName, start, "HIGH", "Risk detected");
            alertRepo.save(alert);
        }

        CapacityAnalysisResultDto res = new CapacityAnalysisResultDto();
        res.setRisky(risky);
        res.setCapacityByDate(capacityByDate);
        return res;
    }
}
