package com.example.demo.Service;

import com.example.demo.model.TeamCapacityRule;

public interface TeamCapacityRuleService {
    public TeamCapacityRule create(TeamCapacityRule tcr);
    public TeamCapacityRule getByTeam(String teamname);
}
