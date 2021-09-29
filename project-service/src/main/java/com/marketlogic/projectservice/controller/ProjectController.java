package com.marketlogic.projectservice.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marketlogic.projectservice.entity.ProjectEntity;
import com.marketlogic.projectservice.entity.ProjectRecordEntity;
import com.marketlogic.projectservice.exception.ResourceNotFoundException;
import com.marketlogic.projectservice.service.ProjectService;
/**
 * This class is responsible for exposing Rest services for Questions and Answers management
 * @author Rajeev Mitra
 * @version 1.0
 */
@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	/** 
	 * This method is used for creating Projects
	 * @param project
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	@PostMapping(value="/project",produces = { MediaType.APPLICATION_JSON_VALUE },consumes= { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public  ProjectEntity createProject(@RequestBody ProjectEntity project) throws ResourceNotFoundException {
		return projectService.createProject(project);
	}

	
	/**
	 * This method is used for updating a project only . 
	 * @param project
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	@PutMapping(value="/project", produces = { MediaType.APPLICATION_JSON_VALUE },consumes= { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public ProjectEntity updateProject(@RequestBody ProjectEntity project) throws ResourceNotFoundException {
		
		return projectService.updateProject(project);
	}
	
	/**
	 * This is used for deleting project
	 * @param id
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping(value="/project/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public  void   deleteProject( @PathVariable("id") Long id) throws ResourceNotFoundException {
		 projectService.deleteProject(id);
		
	}
	
	@GetMapping(value="/projects",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public  List<ProjectEntity> getAllProjects( @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize) throws ResourceNotFoundException {
		 return projectService.getAllprojects(pageNo, pageSize);
		
	}
	
	@PostMapping(value="/project/{id}/_publish",produces = { MediaType.APPLICATION_JSON_VALUE },consumes= { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public  ProjectRecordEntity publish(@PathVariable("id") Long id) throws ResourceNotFoundException, JSONException {
		
		return projectService.completeProject(id);
	}
}
