package com.thetestingacademy.tests.sample;


import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestIntegrationSample extends BaseTest {

    /*
    Create a booking.
    Verify/get a booking using bookingID.
    update a booking.
    delete a booking.
     */

    @Test(groups = "qa", priority = 1)
    @Description("Create a Booking")
    public void CreateBooking(ITestContext iTestContext) {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .body(payLoadManager.CreatePayloadBookingAsString());

        //RestAssured.given(requestSpecification)

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //get booking id from booking response class.
        BookingResponse bookingResponse = payLoadManager.bookingResponseJava(response.asString());
        System.out.println("Booking ID : " + bookingResponse.getBookingid());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());

    }

    @Test(groups = "qa", priority = 2)
    @Description("Get a Booking")
    public void GetBooking(ITestContext iTestContext) {

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String path = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(path).log().all();
        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //AssertJ
        //assertions using AssertJ can be validated when response is in object form, So we will convert booking reponse to object.
        Booking booking = payLoadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isEqualTo("Jim");
        assertThat(booking.getLastname()).isNotNull().isEqualTo("Brown");
        assertThat(booking.getTotalprice()).isNotNull().isPositive().isEqualTo(111);
        assertThat(booking.getDepositpaid()).isNotNull().isEqualTo(true);
        assertThat(booking.getBookingdates().getCheckin()).isNotNull().isEqualTo("2018-01-01");
        assertThat(booking.getBookingdates().getCheckout()).isNotNull().isEqualTo("2019-01-01");
        assertThat(booking.getAdditionalneeds()).isNotNull().isNotEmpty().isEqualTo("Breakfast");

    }


    @Test(groups = "qa", priority = 3)
    @Description("Update a Booking")
    public void UpdateBooking(ITestContext iTestContext) {
        //token
        String token = tokenCreation();
        iTestContext.setAttribute("token",token);

        //code for : Update Booking details for a specific booking id.
        //path
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String path = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(path);

        response = RestAssured.given(requestSpecification).cookies("token",token)
                .when().body(payLoadManager.updatePayloadBookingAsString()).put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //AssertJ
        //assertions using AssertJ can be validated when response is in object form, So we will convert booking reponse to object.
        Booking booking = payLoadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isEqualTo("Naveen");
        assertThat(booking.getLastname()).isNotNull().isEqualTo("Kumar");
        assertThat(booking.getTotalprice()).isNotNull().isPositive().isEqualTo(100);
        assertThat(booking.getDepositpaid()).isNotNull().isEqualTo(false);
        assertThat(booking.getBookingdates().getCheckin()).isNotNull().isEqualTo("1995-03-28");
        assertThat(booking.getBookingdates().getCheckout()).isNotNull().isEqualTo("1995-03-30");
        assertThat(booking.getAdditionalneeds()).isNotNull().isNotEmpty().isEqualTo("Dinner");

    }

    @Test(groups = "qa", priority = 4)
    @Description("Delete a Booking")
    public void DeleteBooking(ITestContext iTestContext) {
        //token
        String token = (String) iTestContext.getAttribute("token");

        //code for : Update Booking details for a specific booking id.
        //path
        Integer bookingid1 = (Integer) iTestContext.getAttribute("bookingid");
        String path = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid1;

        requestSpecification.basePath(path).cookies("token",token);

        validatableResponse = RestAssured.given(requestSpecification).when().delete().then().log().all();

        validatableResponse.statusCode(201);

    }
}

