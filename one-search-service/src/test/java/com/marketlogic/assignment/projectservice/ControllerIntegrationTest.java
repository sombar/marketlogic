//package com.marketlogic.assignment.projectservice;
//
//import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.greaterThan;
//import static org.hamcrest.Matchers.hasSize;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.marketlogic.assignment.projectservice.entity.AnswersEntity;
//import com.marketlogic.assignment.projectservice.entity.QuestionEntity;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
///**
// * This class is responsible for Performing all Integration Testing activities
// * Exception paths are not cover and under TODO list
// * @author Rajeev Mitra
// *
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class ControllerIntegrationTest {
//	
//	@LocalServerPort
//    int port; 
//	
//	@Before
//    public void setUp() { 
//        RestAssured.port = port;
//    }
//	
//	  
//	 
//	 
//	@Test
//	public void testa_addQuestions() {
//		QuestionEntity question = new QuestionEntity();
//		question.setQuestionDescription("Is Michale Schumacer is best ever driver");
//		List<AnswersEntity> answerList = new ArrayList<AnswersEntity> ();
//		AnswersEntity answera = new AnswersEntity();
//		AnswersEntity answerb = new AnswersEntity();
//		answera.setAnswerDescription("Yes he is");
//		answerList.add(answera); 
//		answerb.setAnswerDescription("No he is not");
//		question.setPossibleAnswers(answerList);
//		answerList.add(answerb); 
//		
//		given().contentType(ContentType.JSON).body(question)
//		.when()
//        .post("/question")
//        .then()
//        .statusCode(201).and().assertThat().body("questionId", greaterThan(0));
//	}
//	
//	@Test
//	public void testb_getQuestionById() {
//		  when()
//         .get("/question/1")
//         .then()
//         .statusCode(200).and().assertThat().body("questionDescription", equalTo("Is Michale Schumacer is best ever driver"));
//	}
//	
//	@Test
//	public void testc_getAllQuestions() {
//		  when()
//         .get("/question")
//         .then().contentType(ContentType.JSON)
//         .statusCode(200).and().assertThat().
//          body("size()", equalTo(1));
//	}
//	
//	@Test
//	public void testd_updateQuestion() {
//		  
//		QuestionEntity question = new QuestionEntity();
//		question.setQuestionDescription("Is Michale Schumacer is best ever driver in formula one");
//		question.setQuestionId(1l);
//		
//		given().contentType(ContentType.JSON).body(question)
//		.when()
//        .put("/question")
//        .then()
//        .statusCode(200).and().assertThat().
//         body("questionDescription", equalTo("Is Michale Schumacer is best ever driver in formula one"));
//         
//       }
//	
//	  
//	@Test
//	public void teste_DeleteQuestion() {
//		  when()
//         .delete("/question/1")
//         .then()
//         .statusCode(204);
//		  when()
//	         .get("/question/1")
//	         .then()
//	         .statusCode(200).and().assertThat().body("deleteYN", equalTo("Y"));
//	}
//	
//	@Test
//	public void testf_addAnswersToQuestion() {
//		QuestionEntity question = new QuestionEntity();
//		question.setQuestionId(1l);
//		List<AnswersEntity> answerList = new ArrayList<AnswersEntity> ();
//		AnswersEntity answera = new AnswersEntity();
//		AnswersEntity answerb = new AnswersEntity();
//		answera.setAnswerDescription("Yes");
//		answerList.add(answera); 
//		answerb.setAnswerDescription("No");
//		question.setPossibleAnswers(answerList);
//		answerList.add(answerb); 
//
//		 given().contentType(ContentType.JSON).body(question)
//		.when()
//        .put("/question/answers")
//        .then()
//        .statusCode(200).and().assertThat().body("possibleAnswers", hasSize(2));
//		
//	}
//	
//	
//	@Test
//	public void testi_deleteAnswerToQuestion() {
//		 when()
//		 .delete("/question/1/answer/4")
//		 .then().statusCode(204);
//		
//	}
//	 
//	
//	
//	 
//	
//	
//	
//	
//	
//
//}
