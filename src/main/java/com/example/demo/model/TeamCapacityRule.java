package com.example.demo.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Service.TeamCapacityRuleService;
import com.example.demo.model.TeamCapacityRule;
import com.example.demo.repository.TeamCapacityConfigRepository;
@Service
public class TeamCapacityRuleImpl implements TeamCapacityRuleService {
    @Autowired
    TeamCapacityConfigRepository tccr;

    @Override
    public TeamCapacityRule create(TeamCapacityRule tcr){
        return tccr.save(tcr);
    }

    @Override
    public TeamCapacityRule getByTeam(String teamname){
        return tccr.findByTeamName(teamname);
    }

}
