package com.marketlogic.projectservice;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.marketlogic.projectservice.entity.ProjectEntity;
import com.marketlogic.projectservice.entity.SectionEntity;
import com.marketlogic.projectservice.entity.Status;
import com.marketlogic.projectservice.entity.Type;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/**
 * This class is responsible for Performing all Integration Testing activities
 * Exception paths are not cover and under TODO list
 * @author Rajeev Mitra
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerIntegrationTest {
	
	@LocalServerPort
    int port; 
	
	@Before
    public void setUp() { 
        RestAssured.port = port;
    }
	
	  
	 
	 
	@Test
	public void testa_create_project() {
		ProjectEntity project = new ProjectEntity();
		project.setTitle("Title 1");
		project.setDescription("Project Description" );
		List<SectionEntity> sectionEntityList = new ArrayList<SectionEntity> ();
		SectionEntity section1 = new SectionEntity();
		SectionEntity section2 = new SectionEntity();
		section1.setTitle("Section 1 Title");
		section1.setDescription("Section 1 Description"); 
		section2.setTitle("Section 2 Title");
		section2.setDescription("Section 2 Description");
		sectionEntityList.add(section1); 
		sectionEntityList.add(section2);
		project.setType(Type.TYPE1);
		project.setStatus(Status.DRAFT);
		project.setSections(sectionEntityList);
		
		given().contentType(ContentType.JSON).body(project)
		.when()
        .post("/project")
        .then()
        .statusCode(201).and().assertThat().body("projectId", greaterThan(0));
	}
	
	@Test
	public void testb_projectUpdate() {
		ProjectEntity project = new ProjectEntity();
		project.setTitle("Title 1");
		project.setDescription("Project Description" );
		List<SectionEntity> sectionEntityList = new ArrayList<SectionEntity> ();
		SectionEntity section1 = new SectionEntity();
		SectionEntity section2 = new SectionEntity();
		section1.setTitle("Section 1 Title");
		section1.setDescription("Section 1 Description"); 
		section2.setTitle("Section 2 Title");
		section2.setDescription("Section 2 Description");
		sectionEntityList.add(section1); 
		sectionEntityList.add(section2);
		project.setType(Type.TYPE1);
		project.setStatus(Status.PUBLISHED);
		project.setSections(sectionEntityList);
		project.setProjectId((long) 1);
		given().contentType(ContentType.JSON).body(project)
		.when()
        .post("/project")
        .then()
        .statusCode(201).and().assertThat().body("projectId", greaterThan(0));
		 
	}
	
	@Test
	public void testc_getAllProjects() {
		  when()
         .get("/projects?pageNo=0")
         .then().contentType(ContentType.JSON)
         .statusCode(200).and().assertThat().
          body("size()", equalTo(1));
	}

}
