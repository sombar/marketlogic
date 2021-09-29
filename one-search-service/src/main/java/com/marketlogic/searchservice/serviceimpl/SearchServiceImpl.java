package com.marketlogic.searchservice.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketlogic.searchservice.entity.SearchEntity;
import com.marketlogic.searchservice.exception.ResourceNotFoundException;
import com.marketlogic.searchservice.repository.SearchRepository;
import com.marketlogic.searchservice.service.SearchService;
import com.marketlogic.searchservice.util.ServiceUtil;
/**
 * This service class is responsible for providing all Repository interactions
 * @author Rajeev Mitra
 *
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService{

	@Autowired
	SearchRepository searchRepository;

	@Override
	public SearchEntity create(SearchEntity searchEntity) {
		return searchRepository.save(searchEntity);
	}

	@Override
	public List<SearchEntity> find(String query) throws ResourceNotFoundException{
		return searchRepository.findAllByContentContainingIgnoreCase(query);
	}
	
	
	
}
