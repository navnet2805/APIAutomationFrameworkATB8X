package com.thetestingacademy.tests.sample;


import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationSample {

    /*
    Create a booking.
    get a booking.
    update a booking.
    delete a booking.
     */

    @Test(groups = "qa", priority = 1)
    @Description("Create a Booking")
    public void CreateBooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 2)
    @Description("Get a Booking")
    public void GetBooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 3)
    @Description("Update a Booking")
    public void UpdateBooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups = "qa", priority = 4)
    @Description("Delete a Booking")
    public void DeleteBooking()
    {
        Assert.assertTrue(true);
    }





}
