package com.example.demo.Controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.TeamCapacityService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/capacity-alerts")    
public class CapacityAlertController {
    @Autowired
    TeamCapacityService car;

    @PostMapping("/analyze")
    public String Analyze(@RequestParam String teamName,@RequestParam Date startDate,@RequestParam Date endDate) {
       
        
        return car.analyze(teamName,startDate,endDate);
    }

     @GetMapping("/team/{teamName}")
    public String getAlerts(@RequestParam String teamName) {
       
        
        return car.getAlert(teamName);
    }
    
}
