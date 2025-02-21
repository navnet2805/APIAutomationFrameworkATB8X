package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayLoadManager;
import com.thetestingacademy.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TestVerifyCreateBookingPOST01 extends BaseTest{

    @Owner("naveen2805")
    @Description("Verify the POST request is working fine")
    @Test
    public void testVerifyCreatePost01()
    {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .when()
                .body(payLoadManager.CreatePayloadBookingAsString())
                .post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


        //Now we will validate using multiple assertions

        //Default Rest Assured assertions
        validatableResponse.body("booking.firstname", Matchers.equalTo("Jim"));
        validatableResponse.body("booking.lastname",Matchers.equalTo("Brown"));
        validatableResponse.body("booking.totalprice",Matchers.equalTo(111));
        validatableResponse.body("booking.depositpaid",Matchers.equalTo(true));
        validatableResponse.body("booking.bookingdates.checkin",Matchers.equalTo("2018-01-01"));
        validatableResponse.body("booking.bookingdates.checkout",Matchers.equalTo("2019-01-01"));
        validatableResponse.body("booking.additionalneeds",Matchers.equalTo("Breakfast"));



        //AssertJ
        //assertions using AssertJ can be validated when response is in object form, So we will convert booking reponse to object.
        BookingResponse bookingResponse = payLoadManager.bookingResponseJava(response.asString());
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isEqualTo("Jim");
        assertThat(bookingResponse.getBooking().getLastname()).isNotNull().isEqualTo("Brown");
        assertThat(bookingResponse.getBooking().getTotalprice()).isNotNull().isPositive().isEqualTo(111);
        assertThat(bookingResponse.getBooking().getDepositpaid()).isNotNull().isEqualTo(true);
        assertThat(bookingResponse.getBooking().getBookingdates().getCheckin()).isNotNull().isEqualTo("2018-01-01");
        assertThat(bookingResponse.getBooking().getBookingdates().getCheckout()).isNotNull().isEqualTo("2019-01-01");
        assertThat(bookingResponse.getBooking().getAdditionalneeds()).isNotNull().isNotEmpty().isEqualTo("Breakfast");

        //using TestNG assertions
        assertActions.VerifyStatusCode(response, 200);


        System.out.println("Booking ID : " +bookingResponse.getBookingid());












    }


}
