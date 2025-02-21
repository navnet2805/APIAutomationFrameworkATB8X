package com.thetestingacademy.asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class AssertActions {
    //Common assertions which can be reused

    public void VerifyResponseBody(String actual, String expected, String Description)
    {
        assertEquals(actual,expected,Description);
    }

    public void VerifyResponseBody(int actual, int expected, String Description)
    {
        assertEquals(actual,expected,Description);
    }

    public void VerifyStatusCode(Response response, Integer expected)
    {
        assertEquals(response.getStatusCode(), expected);
    }




}
