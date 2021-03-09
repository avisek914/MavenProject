package com.hrms.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class hardCodedExamples {
    String baseUri=RestAssured.baseURI="http://3.237.189.167/syntaxapi/api";
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTQzOTc1MTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxNDQ0MDcxOCwidXNlcklkIjoiMjMxMiJ9.E0WQx-8laruAG4xgDBc2mf1xUy4nrQpr8rT43Bo4Gkk";

    @Test
    public void sampleTest(){

      //preparing request to get one employee
      RequestSpecification getOneEmployee = given().header("Authorization",token).header("Content-Type","Application/json").queryParam("employee_id","13001");
        //sending the request to the endpoint
        Response oneEmployee = getOneEmployee.when().get("/getOneEmployee.php");
        oneEmployee.prettyPrint();
//        System.out.println(oneEmployee.asString());
        oneEmployee.then().assertThat().statusCode(200);

    }

    @Test
    public void postCreateEmployee(){
        RequestSpecification createEmployeeRequest = given().header("Authorization", token).header("Content-Type", "Application/json").queryParam("employee_id", "13001").body("{\n" +
                "  \"emp_firstname\": \"Abhishek\",\n" +
                "  \"emp_lastname\": \"Rajdhami\",\n" +
                "  \"emp_middle_name\": \"\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1993-02-18\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"IT Analyst\"\n" +
                "}");

       Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");
       createEmployeeResponse.prettyPrint();
       createEmployeeResponse.then().assertThat().statusCode(201);
       String employeeId = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
        System.out.println(employeeId);


        JsonPath js=createEmployeeResponse.jsonPath();
        String employeeIDjSon=js.getString("Employee[0].employee_id");
        System.out.println(employeeIDjSon);
        assertThat(employeeIDjSon,equalTo("16090A"));


        createEmployeeResponse.then().assertThat().body("Message",equalTo("Entry Created"));
        createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname",equalTo("Abhishek"));


    }

    @Test
    public void getCreatedEmployee(){
        RequestSpecification getCreatedEmployeerequest = given().header("Content-Type","Application/json").header("Authorization",token).queryParam("employee_id","16090A");
        Response getCEmployeeresponse=getCreatedEmployeerequest.when().get("/getOneEmployee.php");
        getCEmployeeresponse.prettyPrint();
        String empId=getCEmployeeresponse.body().jsonPath().getString("employee[0].employee_id");

        boolean verifyEmployeeID=empId.equalsIgnoreCase("16090A");
        System.out.println(verifyEmployeeID);
        Assert.assertTrue(verifyEmployeeID);
        getCEmployeeresponse.then().assertThat().body("employee[0].employee_id",equalTo("16090A"));

    }

    @Test
    public void partiallyUpdateEmployee(){
        RequestSpecification partiallyUpdateRequest=given().header("Content-Type","Application/json").header("Authorization",token).body("{\"employee_id\": \"16090A\",\n" +
                "\"emp_middle_name\": \"kumar\"\n" +
                "\n" +
                "}");
        Response partiallyUpdateResponse = partiallyUpdateRequest.when().patch("updatePartialEmplyeesDetails.php");
        partiallyUpdateResponse.prettyPrint();

        //assert that body contains the Message Entry updated

        JsonPath js=partiallyUpdateResponse.jsonPath();
        Object Message=js.get("Message");
        System.out.println(Message);
    }
    @Test
    public void deleteEmployeeRequest(){
        RequestSpecification deleteEmployeeRequest = given().header("Content-Type","Application/json").header("Authorization",token).queryParam("employee_id","16489A");
        Response deleteEmployeeResponse=deleteEmployeeRequest.when().delete("/deleteEmployee.php");
       deleteEmployeeResponse.prettyPrint();

       deleteEmployeeResponse.then().assertThat().body("message",containsString("Entry deleted"));


        JsonPath js=deleteEmployeeResponse.jsonPath();
        Object Message=js.get("Message");
        System.out.println(Message);
    }


}

