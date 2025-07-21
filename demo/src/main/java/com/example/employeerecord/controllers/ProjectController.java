
package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.Project;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projService;
    @PostMapping("/createProject")
    public Project createProject(@RequestBody Project project){
        return projService.createProject(project);
    }

    @GetMapping("/fetchProject/{id}")
    public Project fetchProject(@PathVariable long id){
        return projService.fetchProject(id);
    }

    @GetMapping("/fetchAllProject")
    public List<Project> fetchAllProject(){
        return projService.fetchAllProjects();
    }

    @DeleteMapping("/deleteProject/{id}")
    public String delProject(@PathVariable long id){
        return projService.deleteProject(id);
    }

    @PutMapping("/updateProject/{id}")
    public Project updateProject(@PathVariable long id,@RequestBody Project existingProject){
        return projService.updateProject(id,existingProject);
    }
}
