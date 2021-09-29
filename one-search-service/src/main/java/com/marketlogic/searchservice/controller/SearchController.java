package com.marketlogic.searchservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.marketlogic.searchservice.entity.SearchEntity;
import com.marketlogic.searchservice.exception.ResourceNotFoundException;
import com.marketlogic.searchservice.service.SearchService;
/**
 * This class is responsible for exposing Rest services for Questions and Answers management
 * @author Rajeev Mitra
 * @version 1.0
 */
@RestController
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	/** 
	 * This method is used for creating Projects
	 * @param project
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	@PostMapping(value="/create",produces = { MediaType.APPLICATION_JSON_VALUE },consumes= { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public  SearchEntity createSearchService(@RequestBody SearchEntity searchEntity) {
		return searchService.create(searchEntity);
	}

	
	/**
	 * This method is used for searching  only . 
	 * @param query string
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	
	@GetMapping(value="/_search",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public  List<SearchEntity> getAllProjects( @RequestParam String query) throws ResourceNotFoundException {
		 return searchService.find(query);
		
	}
	
}
