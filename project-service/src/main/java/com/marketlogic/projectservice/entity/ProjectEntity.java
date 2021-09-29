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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long projectId;
	 
	@Column(name="Title")
	@NotNull
	private String title;
	
	@Column(name="Description")
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL)
    private List<SectionEntity> sections;
	 
	@Enumerated(EnumType.ORDINAL)
    private Type type;
	
	
	@Enumerated(EnumType.ORDINAL)
    private Status status = Status.DRAFT;
	
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

	public List<SectionEntity> getSections() {
		return sections;
	}

	public void setSections(List<SectionEntity> sections) {
		this.sections = sections;
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
	

}
