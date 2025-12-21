package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.LeaveRequestService;
import com.example.demo.model.LeaveRequest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/approve")
    public String approve(@RequestParam Long id) {
        lrq.approve(id);
        return "For id:"+id+" Leave request have been approved";

    }
    @GetMapping("/reject")
    public String reject(@RequestParam Long id) {
        lrq.reject(id);
        return "For id:"+id+" Leave request have been rejected";
        
    }
    
}
