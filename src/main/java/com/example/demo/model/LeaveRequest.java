package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Id;

public class LeaveRequest {
   @Id
   private Long id;

   EmployeeProfile employee;
   private Date startDate;
   private Date endDate;
   private String type;
   private String status;
   private String reason;
   public Long getId() {
    return id;
   }
   public void setId(Long id) {
    this.id = id;
   }
   public EmployeeProfile getEmployee() {
    return employee;
   }
   public void setEmployee(EmployeeProfile employee) {
    this.employee = employee;
   }
   public Date getStartDate() {
    return startDate;
   }
   public void setStartDate(Date startDate) {
    this.startDate = startDate;
   }
   public Date getEndDate() {
    return endDate;
   }
   public void setEndDate(Date endDate) {
    this.endDate = endDate;
   }
   public String getType() {
    return type;
   }
   public void setType(String type) {
    this.type = type;
   }
   public String getStatus() {
    return status;
   }
   public void setStatus(String status) {
    this.status = status;
   }
   public String getReason() {
    return reason;
   }
   public void setReason(String reason) {
    this.reason = reason;
   }
   
}
