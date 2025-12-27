    package com.example.demo.service.impl;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

import com.example.demo.model.TeamCapacityRule;
    import com.example.demo.repository.TeamCapacityRuleRepository;
import com.example.demo.service.TeamCapacityRuleService;
    @Service
    public class TeamCapacityRuleImpl implements TeamCapacityRuleService {
        @Autowired
        TeamCapacityRuleRepository tccr;

        @Override
        public TeamCapacityRule create(TeamCapacityRule tcr){
            return tccr.save(tcr);
        }

        @Override
        public TeamCapacityRule getByTeam(String teamname){
            return tccr.findByTeamName(teamname);
        }

    }
