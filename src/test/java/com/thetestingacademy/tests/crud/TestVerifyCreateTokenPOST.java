package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestVerifyCreateTokenPOST extends BaseTest {


    @Description("Verify the the token is created POST")
    @Test
    public void testVerifyCreateToken()
    {
        requestSpecification = RestAssured.given(requestSpecification).basePath(APIConstants.AUTH_URL)
                             .body(payLoadManager.createToken()).log().all();

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        String token = response.jsonPath().getString("token");

    }
}
