
package com.example.employeerecord.controllers;

import com.example.employeerecord.dao.Project;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.dto.GenericResponseEntity;
import com.example.employeerecord.dto.UserProfileDto;
import com.example.employeerecord.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final String PROJECT_ADDED_SUCCESSFULLY="Project Added successfully";
    private final String PROJECT_FETCHED_SUCCESSFULLY="Project fetched successfully";
    private final String PROJECTS_FETCHED_SUCCESSFULLY="Projects fetched successfully";
    private final String PROJECT_UPDATED_SUCCESSFULLY="Project updated successfully";
    private final String PROJECT_DELETED_SUCCESSFULLY="Project deleted successfully";
    @Autowired
    ProjectService projService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createProject")
    public GenericResponseEntity<Project> createProject(@RequestBody Project project){
        return GenericResponseEntity.<Project>builder()
                .message(PROJECT_ADDED_SUCCESSFULLY)
                .data(projService.createProject(project))
                .statusCode(201)
                .status(HttpStatus.CREATED)
                .success(true)
                .build();
    }

    @GetMapping("/fetchProject/{id}")
    public GenericResponseEntity<Project> fetchProject(@PathVariable long id){
        return GenericResponseEntity.<Project>builder()
                .message(PROJECT_FETCHED_SUCCESSFULLY)
                .data(projService.fetchProject(id))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @GetMapping("/fetchAllProject")
    public GenericResponseEntity<List<Project>> fetchAllProject(){

        return GenericResponseEntity.<List<Project>>builder()
                .message(PROJECTS_FETCHED_SUCCESSFULLY)
                .data(projService.fetchAllProjects())
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @DeleteMapping("/deleteProject/{id}")
    public GenericResponseEntity<String> delProject(@PathVariable long id){
        return GenericResponseEntity.<String>builder()
                .message(PROJECT_DELETED_SUCCESSFULLY)
                .data(null)
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }

    @PutMapping("/updateProject/{id}")
    public GenericResponseEntity<Project> updateProject(@PathVariable long id,@RequestBody Project existingProject){
        return GenericResponseEntity.<Project>builder()
                .message(PROJECT_UPDATED_SUCCESSFULLY)
                .data(projService.updateProject(id,existingProject))
                .statusCode(200)
                .status(HttpStatus.OK)
                .success(true)
                .build();
    }
}
