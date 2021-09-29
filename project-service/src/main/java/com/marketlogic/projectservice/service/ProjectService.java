package com.marketlogic.projectservice.service;

import java.util.List;

import org.json.JSONException;

import com.marketlogic.projectservice.entity.ProjectEntity;
import com.marketlogic.projectservice.entity.ProjectRecordEntity;
import com.marketlogic.projectservice.exception.ResourceNotFoundException;

public interface ProjectService {

	ProjectEntity createProject(ProjectEntity project) ;

	ProjectEntity updateProject(ProjectEntity question) throws ResourceNotFoundException;

	void deleteProject(Long id) throws ResourceNotFoundException;

	List<ProjectEntity> getAllprojects(Integer pageNo, Integer pageSize) throws ResourceNotFoundException;

	ProjectRecordEntity completeProject(Long id) throws ResourceNotFoundException, JSONException;

	
	
}
