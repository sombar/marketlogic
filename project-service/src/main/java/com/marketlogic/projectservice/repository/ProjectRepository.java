package com.marketlogic.projectservice.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.marketlogic.projectservice.entity.ProjectEntity;

@Repository
public interface ProjectRepository  extends PagingAndSortingRepository <ProjectEntity,Long>{

	Page<ProjectEntity> findAllByDelete(Object object, Pageable paging);
	
	
}
