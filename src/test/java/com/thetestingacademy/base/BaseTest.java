package com.thetestingacademy.base;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayLoadManager;
import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayLoadManager payLoadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp()
    {
        //BASE URL and Content_Type is common for all the test cases.

        payLoadManager = new PayLoadManager();
        assertActions = new AssertActions();

        requestSpecification = RestAssured.given()
                                          .baseUri(APIConstants.BASE_URL)
                                          .contentType(ContentType.JSON)
                                          .log().all();

        //in Some company they use RequestSpecBuilder.

        /*
        requestSpecification = new RequestSpecBuilder().setBaseUri(APIConstants.BASE_URL)
                                .addHeader("Content-Type","application/json")
                                .build().log().all();
        */
    }


    public String tokenCreation()
    {
        //token script
        requestSpecification = RestAssured.given(requestSpecification)
                .basePath(APIConstants.AUTH_URL).log().all();

        //Set the payload
        String payload = payLoadManager.createToken();

        //get the payload
        response = requestSpecification.contentType(ContentType.JSON).body(payload).post();

        //token extraction
        String token = payLoadManager.getTokenFromJSON(response.asString());

        return token;

    }





}
