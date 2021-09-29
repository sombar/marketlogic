package com.marketlogic.searchservice;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.marketlogic.searchservice.entity.SearchEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
	public void testa_addContent() {
		SearchEntity searchEntity = new SearchEntity();
		searchEntity.setContent(" Testing Search Content");
		
		given().contentType(ContentType.JSON).body(searchEntity)
		.when()
        .post("/create")
        .then()
        .statusCode(201).and().assertThat().body("id", greaterThan(0));
	}
	
	@Test
	public void testb_searchContent() {
		  when()
         .get("/_search?query=Content")
         .then()
         .statusCode(200).and().assertThat().body("size()", equalTo(1));
	}
	

}
