package com.marketlogic.projectservice.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.marketlogic.projectservice.entity.ProjectEntity;
import com.marketlogic.projectservice.entity.ProjectRecordEntity;
import com.marketlogic.projectservice.entity.SectionEntity;
import com.marketlogic.projectservice.entity.SectionRecordEntity;
import com.marketlogic.projectservice.entity.Status;
import com.marketlogic.projectservice.exception.ResourceNotFoundException;
import com.marketlogic.projectservice.repository.ProjectRecordRepository;
import com.marketlogic.projectservice.repository.ProjectRepository;
import com.marketlogic.projectservice.service.ProjectService;
import com.marketlogic.projectservice.util.ServiceUtil;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

import com.marketlogic.projectservice.entity.ProjectEntity;
import com.marketlogic.projectservice.repository.ProjectRepository;
import com.marketlogic.projectservice.service.ProjectService;
/**
 * This service class is responsible for providing all Repository interactions
 * @author Rajeev Mitra
 *
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProjectRecordRepository projectRecordRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
	/**
	 * This method is used for updating a project. Existing sections to the project will  be modified in this operation.
	 * If no project found for the given project entity then it will throw ResourceNotFoundException
	 * @throws ResourceNotFoundException 
	 */
	public ProjectEntity updateProject(ProjectEntity projectEntity) throws ResourceNotFoundException {
		ProjectEntity project = projectRepository.findById(projectEntity.getProjectId()).get(); 
		if(project==null) {
			throw new ResourceNotFoundException(ServiceUtil.INVALID_PROJECT_DESC, ServiceUtil.INVALID_PROJECT_EX_CODE);
		}
		projectEntity.setSections(project.getSections());
		return projectRepository.save(projectEntity);
	}
	
	/**
	 * This method deletes Project and set delete flag as Y , so that data will not be lost 
	*/
	
	public void deleteProject(Long id) throws ResourceNotFoundException {
		ProjectEntity projectEntity = projectRepository.findById(id).get(); 
		if(projectEntity==null) {
			throw new ResourceNotFoundException(ServiceUtil.INVALID_PROJECT_DESC, ServiceUtil.INVALID_PROJECT_EX_CODE);
		}
		// Soft deleting the published projects 
		if(projectEntity.getStatus().equals(Status.PUBLISHED)) {
			projectEntity.setDelete("Y");
			projectRepository.save(projectEntity);
		}else {
			projectRepository.delete(projectEntity);
		}
	}

	@Override
	public ProjectEntity createProject(ProjectEntity projectEntity) throws ResourceNotFoundException{
		if(projectEntity.getTitle().isEmpty()) {
			throw new ResourceNotFoundException(ServiceUtil.REQUIRED_FIELD_MISSING_DESC, ServiceUtil.INVALID_PROJECT_EX_CODE);
		}
		return projectRepository.save(projectEntity);
	}

	@Override
	public List<ProjectEntity> getAllprojects(Integer pageNo, Integer pageSize) throws ResourceNotFoundException {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<ProjectEntity> pagedResult = projectRepository.findAllByDelete(null,paging);
		if(pagedResult.hasContent()) {
            return pagedResult.getContent();
		}else {
			throw new ResourceNotFoundException(ServiceUtil.INVALID_PROJECT_DESC, ServiceUtil.INVALID_PROJECT_EX_CODE);
		}
         
		
	}
	
	@Override
	public ProjectRecordEntity completeProject(Long id) throws ResourceNotFoundException, JSONException {
		ProjectEntity project = projectRepository.findById(id).get(); 
		if(project==null) {
			throw new ResourceNotFoundException(ServiceUtil.INVALID_PROJECT_DESC, ServiceUtil.INVALID_PROJECT_EX_CODE);
		}
		ProjectRecordEntity projectRecord = null;
		Optional<ProjectRecordEntity> projectRecordEntity = projectRecordRepository.findById(id);
		if(!projectRecordEntity.isPresent()) {
			projectRecord = new ProjectRecordEntity();
			BeanUtils.copyProperties(project, projectRecord);
			List<SectionRecordEntity> SectionRecordEntityList = new ArrayList<SectionRecordEntity>();
			for(SectionEntity section : project.getSections()) {
				SectionRecordEntity secRecordEntity = new SectionRecordEntity();
				secRecordEntity.setDescription(section.getDescription());
				secRecordEntity.setTitle(section.getTitle());
				secRecordEntity.setSectionId(section.getSectionId());
				SectionRecordEntityList.add(secRecordEntity);
			}
			projectRecord.setSectionRecord(SectionRecordEntityList);
			publish(projectRecord);
			return (projectRecordRepository.save(projectRecord));
		}else {
			projectRecord = projectRecordEntity.get();
			publish(projectRecord);
			return projectRecord;
		}		
		
		
		
	}
	
	private void publish(ProjectRecordEntity projectRecord) throws JSONException {
	    String url = "http://localhost:8081/create";
	    // create headers
	    HttpHeaders headers = new HttpHeaders();
	    // set `content-type` header
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    // set `accept` header
	    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    JSONObject request = new JSONObject();
	    request.put("content", projectRecord.toString());
	    HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);
	    restTemplate.postForObject(url, entity, ProjectRecordEntity.class);
	   
	}

	
	
	
	
	
	
	
}
