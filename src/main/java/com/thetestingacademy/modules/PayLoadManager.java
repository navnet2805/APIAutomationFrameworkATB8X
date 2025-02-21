package com.thetestingacademy.modules;

import com.google.gson.Gson;
import com.thetestingacademy.pojos.*;

public class PayLoadManager {

    //Converting JAVA object to String
   public String CreatePayloadBookingAsString()
   {
       Booking booking = new Booking();
       booking.setFirstname("Jim");
       booking.setLastname("Brown");
       booking.setTotalprice(111);
       booking.setDepositpaid(true);

       Bookingdates bookingdates = new Bookingdates();
       bookingdates.setCheckin("2018-01-01");
       bookingdates.setCheckout("2019-01-01");

       booking.setBookingdates(bookingdates);
       booking.setAdditionalneeds("Breakfast");

       System.out.println(booking); // booking is in the form of object.

       //Convert java object to String/Json. This can be done using Gson.
       Gson gson = new Gson();
       String JsonStringpaylod = gson.toJson(booking);

       return  JsonStringpaylod; // is in the form of String or Json.
   }


    public BookingResponse bookingResponseJava(String responseString)
    {
        Gson gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    public Booking getResponseFromJSON(String responseString)
    {
        Gson gson = new Gson();
        Booking booking = gson.fromJson(responseString, Booking.class);
        return booking;

    }


    public String updatePayloadBookingAsString()
    {
        Booking booking = new Booking();
        booking.setFirstname("Naveen");
        booking.setLastname("Kumar");
        booking.setTotalprice(100);
        booking.setDepositpaid(false);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("1995-03-28");
        bookingdates.setCheckout("1995-03-30");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Dinner");

        System.out.println(booking); // booking is in the form of object.

        //Convert java object to String/Json. This can be done using Gson.
        Gson gson = new Gson();
        String JsonStringpaylod = gson.toJson(booking);

        return  JsonStringpaylod; // is in the form of String or Json.
    }


    public String createToken()
    {
        Token token = new Token();
        token.setUsername("admin");
        token.setPassword("password123");

        System.out.println(token);// token is in the form of object.

        //Convert java object to String/Json. This can be done using Gson.
        Gson gson = new Gson();
        String JsonStringToken = gson.toJson(token);

        return  JsonStringToken; // is in the form of String/Json.
    }

    public String getTokenFromJSON(String tokenresponse1)
    {
        Gson gson = new Gson();
        TokenResponse tokenResponse = gson.fromJson(tokenresponse1,TokenResponse.class);
        return tokenResponse.getToken();
    }






}
