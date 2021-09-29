package com.marketlogic.searchservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketlogic.searchservice.entity.SearchEntity;

@Repository
public interface SearchRepository  extends JpaRepository <SearchEntity,Long>{


	List<SearchEntity> findAllByContentContainingIgnoreCase(String query);

	
	
	
}
