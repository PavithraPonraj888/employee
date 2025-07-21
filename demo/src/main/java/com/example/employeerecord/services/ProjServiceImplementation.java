package com.example.employeerecord.services;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dao.Project;
import com.example.employeerecord.exceptions.ResourceNotFoundException;
import com.example.employeerecord.repository.ProjectRepo;
import com.example.employeerecord.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjServiceImplementation implements ProjectService {
    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public Project updateProject(Long projId,Project updatedProject) {
        Project existingProject=projectRepo.findById(projId).
                orElseThrow(()-> new ResourceNotFoundException("Project "+projId+" not found"));
        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDuration(updatedProject.getDuration());
        existingProject.setStartDate(updatedProject.getStartDate());
        return projectRepo.save(existingProject);
    }

    @Override
    public String deleteProject(Long projId) {
        Project project=projectRepo.findById(projId).orElseThrow(()->new ResourceNotFoundException("Project "+projId+" not found"));
        List<Employees> employeesList=project.getEmployeesList();
        for(Employees emp:employeesList){
            emp.getProjects().remove(project);
        }
        projectRepo.deleteById(projId);
        return "Deleted Successfully";
    }

    @Override
    public Project fetchProject(Long projId) {
        return projectRepo.findById(projId).orElseThrow(()-> new ResourceNotFoundException("Project "+projId+" not found"));
    }

    @Override
    public List<Project> fetchAllProjects() {
        return projectRepo.findAll();
    }
}
