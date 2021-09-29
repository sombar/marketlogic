package com.marketlogic.searchservice.service;

import java.util.List;

import com.marketlogic.searchservice.entity.SearchEntity;
import com.marketlogic.searchservice.exception.ResourceNotFoundException;

public interface SearchService {

	SearchEntity create(SearchEntity searchEntity);

	List<SearchEntity> find(String query) throws ResourceNotFoundException;

	
	
}
