package com.example.employeerecord.services;

import com.example.employeerecord.dao.Project;

import java.util.List;

public interface ProjectService {
    public Project createProject(Project project);
    public Project updateProject(Long projId,Project updatedProject);
    public String deleteProject(Long projId);
    public Project fetchProject(Long projId);
    public List<Project> fetchAllProjects();
}