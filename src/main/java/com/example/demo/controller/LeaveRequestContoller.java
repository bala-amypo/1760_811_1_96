package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.LeaveRequestService;
import com.example.demo.model.LeaveRequest;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestContoller {
    
    @Autowired
    LeaveRequestService lrq;

    @PostMapping("/create")
    public LeaveRequest create(@RequestBody LeaveRequest lq) {
        return lrq.create(lq);
    }
    @PutMapping("/approve")
    public String approve(@RequestParam Long id) {
        lrq.approve(id);
        return "For id:"+id+" Leave request have been approved";

    }
    @PutMapping("/reject")
    public String reject(@RequestParam Long id) {
        lrq.reject(id);
        return "For id:"+id+" Leave request have been rejected";
        
    }
    
}
