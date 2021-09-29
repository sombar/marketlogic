package com.marketlogic.projectservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketlogic.projectservice.entity.ProjectRecordEntity;

@Repository
public interface ProjectRecordRepository  extends JpaRepository <ProjectRecordEntity,Long>{

}
