
package com.example.employeerecord.mapper;

import com.example.employeerecord.dao.Employees;
import com.example.employeerecord.dao.Project;
import com.example.employeerecord.dto.EmployeeDto;
import com.example.employeerecord.dto.ProjectDto;

import java.util.List;

public class ProjectMapper {
    public static ProjectDto toProjectDto(Project project){
        ProjectDto projectDto=new ProjectDto();
        projectDto.setProjId(project.getProjId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDuration(project.getDuration());
        projectDto.setStartDate(project.getStartDate());
        return projectDto;
    }
    public static List<ProjectDto> toProjectDtoList(List<Project> projectsList) {
        return projectsList.stream()
                .map(ProjectMapper::toProjectDto)
                .toList();
    }
}

