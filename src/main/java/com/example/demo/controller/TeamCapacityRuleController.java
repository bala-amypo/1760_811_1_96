package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.TeamCapacityRuleService;
import com.example.demo.model.TeamCapacityRule;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/capacity-rules")
public class TeamCapacityRuleController {
   
    @Autowired
    TeamCapacityRuleService tcrs;

@PostMapping("/create")
public TeamCapacityRule create(@RequestBody TeamCapacityRule entity) {
    
    
    return tcrs.create(entity);
}


    @GetMapping("/getByTeam")
    public TeamCapacityRule getByTeam(@RequestParam String teamname) {
        return tcrs.getByTeam(teamname);
    }

    
}
