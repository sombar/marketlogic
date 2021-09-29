# Design Decisions and Short Design Notes

# Requirement

The requirments of the assignment is as a follows

          a. A project with some states and having multiple sections to be designed
          b. The project when published will create a snapshot and push to another micro service called one search 
          c. one search will allow searching the projects
 Based on the above condition design REST inetraced to create /update / delete / publish / get all projects. User should able to do all the operations 
 
 # Key Design Principle 
 
 The project is developed based on the microservice principles .  As per given requirement basic information model is as follows.
          a. One to many relationship managed through spring data jpa between project and section
          b. Replica project record and section record entities created to hold the snapshot when published
          c. Publishing will push the snapshot version as a serialized string to search service , user do not need to do anything 
 
 # Domain Driven Principle and Microservice Identification 
 
 Application is decomposed into two basic domains 
            a. Project Domain 
            b. Section Domain 
            
 
 # Application Architecture  and Technology Selection
 
 Spring boot and JPA is used for developing Microservices as Spring-boot is preferred framework as per given requirement.  
 Application architecture follows Hexagonal architecture or Ports and Adapter pattern. Core to which is domain which is insulated from 
 any database specific or vendor soecific locking . It is accessed by a Repository and top of which a spring service layer. 
 All Rest services are exposed using Spring Rest Controller and accept request using HTTP protocol . Optionally it can be extended to  support HTTPS also. 
 
 
 # Exposed REST API's
 
 Following are the APIs exposed for Project service and its purpose.
 
             a. Project Create - /project - HTTP POST
             b. Project Update - /project - HTTP PUT
             c. Project Retrieve - /projects?pageNo=<optional>&pageSize=<optional> - HTTP GET ( Default to 0 and 10 respectively )
             d. Project Delete - /project/<id> - Pass project id for deletion - HTTP DELETE ( When status is DRAFT , it is hard deleted other wise soft deleted )
             e. Project Publish - /project/<id>/_publish - Pass project id for publishing 
 
 
 Following are APIs exposed for Search Service and its purpose.
            
            a. One Search Create - /create - HTTP POST
            b. One Search Search - /_search?query=<Text> - HTTP GET 
 
 
 
 # Testability 
 All Rest Services are tested using Integration test script using RestAssured Testing framework . In current test cases exception paths are not covered
 
 # Compile , test and Package 
 
 To compile and integration test the application run following commands from project's root directory 
 
                             mvn clean compile test
                             
  To package issue following commands
  
                             mvn package
                             
   This will create a jar file in target directory of each projects.
   
 * For compilation , machine need to have maven setup .                            
 
 # Deployability and Pre-requisite 
 
 As per pre-requisite jdk 1.8.x is required to be installed in the m/c where these services will run .
 It can be deployed in any OS where jdk 1.8.x is istalled . It is tested in MacOS. 
 Docker files are created to containerize the services and additionally K8s deployment files are created to deploy it to any K8s cluster.
 It allows to horizontally scale the application as per requirement.
 
 # Assumption
 1. There will not be any hard delete for Project Entity when in published state. A delete flag is used for mark the resource deletion.
 2. Searching for all projects will retrieve only projects which are not hard or soft deleted .
 3. Publishing already published projects will not persist it again or send it to the search service. 
 4. In memory databased is used.
 
 
 # Area of Improvement
1.  Open API Specification documentation can be used to describe APIs.
2.  Security and Logging are not implemented and can be augmented later. 
3.  Test cases can be robust but in the interest of time , only created sample rest assured cases .
