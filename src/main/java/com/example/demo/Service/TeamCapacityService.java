package com.example.demo.Service;

import java.sql.Date;

public interface TeamCapacityService  {
        public String analyze(String teamName,Date startDate,Date endDate);
        public String getAlert(String teamName);
    
}
