package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeProfileServiceImpl implements com.example.demo.Service.EmployeeProfileService {
    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) { this.repo = repo; }

    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile e = new EmployeeProfile();
        e.setEmployeeId(dto.getEmployeeId());
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setTeamName(dto.getTeamName());
        e.setRole(dto.getRole());
        EmployeeProfile saved = repo.save(e);
        EmployeeProfileDto r = new EmployeeProfileDto();
        r.setId(saved.getId());
        r.setEmployeeId(saved.getEmployeeId());
        r.setFullName(saved.getFullname());
        r.setEmail(saved.getEmail());
        r.setTeamName(saved.getTeamName());
        r.setRole(saved.getRole());
        return r;
    }

    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        EmployeeProfile existing = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (dto.getFullName() != null) existing.setFullname(dto.getFullName());
        if (dto.getTeamName() != null) existing.setTeamName(dto.getTeamName());
        if (dto.getRole() != null) existing.setRole(dto.getRole());
        EmployeeProfile saved = repo.save(existing);
        EmployeeProfileDto r = new EmployeeProfileDto();
        r.setId(saved.getId());
        r.setEmployeeId(saved.getEmployeeId());
        r.setFullName(saved.getFullname());
        r.setTeamName(saved.getTeamName());
        r.setRole(saved.getRole());
        return r;
    }

    public void deactivate(Long id) {
        EmployeeProfile e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        e.setActive(false);
        repo.save(e);
    }

    public EmployeeProfileDto getById(Long id) {
        EmployeeProfile e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        EmployeeProfileDto r = new EmployeeProfileDto();
        r.setId(e.getId());
        r.setEmployeeId(e.getEmployeeId());
        r.setFullName(e.getFullname());
        return r;
    }

    public List<EmployeeProfileDto> getByTeam(String team) {
        return repo.findByTeamNameAndActiveTrue(team).stream().map(e -> {
            EmployeeProfileDto dto = new EmployeeProfileDto();
            dto.setId(e.getId());
            dto.setEmployeeId(e.getEmployeeId());
            dto.setFullName(e.getFullname());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<EmployeeProfileDto> getAll() {
        return repo.findAll().stream().map(e -> {
            EmployeeProfileDto dto = new EmployeeProfileDto();
            dto.setId(e.getId());
            dto.setEmployeeId(e.getEmployeeId());
            dto.setFullName(e.getFullname());
            return dto;
        }).collect(Collectors.toList());
    }
}
