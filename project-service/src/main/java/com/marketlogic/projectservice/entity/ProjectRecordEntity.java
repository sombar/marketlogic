package com.marketlogic.projectservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Immutable
@Table(name = "PROJECT_RECORD")
public class ProjectRecordEntity {
	
	@Id
	Long projectId;
	 
	@Column(name="Title")
	private String title;
	
	@Column(name="Description")
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL)
    private List<SectionRecordEntity> sectionRecord;
	 
	@Enumerated(EnumType.ORDINAL)
    private Type type;
	
	@Enumerated(EnumType.ORDINAL)
    private Status status;
	
	@Column(name="Delete")
	@JsonIgnore
	private String delete;
	

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}
	
	public List<SectionRecordEntity> getSectionRecord() {
		return sectionRecord;
	}

	public void setSectionRecord(List<SectionRecordEntity> sectionRecord) {
		this.sectionRecord = sectionRecord;
	}

	@Override
	public String toString() {
		StringBuffer sectionContent = new StringBuffer();
		sectionContent.append(title);
		sectionContent.append(" ");
		sectionContent.append(description);
		
		for(SectionRecordEntity section : getSectionRecord()) {
			sectionContent.append(" ");
			sectionContent.append(section.getTitle());
			sectionContent.append(" ");
			sectionContent.append(section.getDescription());
		}
		return sectionContent.toString();	
	}

	
	

}
