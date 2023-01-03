package com.restful.booker.steps;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class BookingSteps {
    @Step
    public ValidatableResponse createBooking(String name, String lName, int price,
                                             boolean result, HashMap<String,String> dates,String needs){
        BookingPojo bookingPojo= new BookingPojo();
        bookingPojo.setFirstname(name);
        bookingPojo.setLastname(lName);
        bookingPojo.setTotalprice(price);
        bookingPojo.setDepositpaid(result);
        bookingPojo.setBookingdates(dates);
        bookingPojo.setAdditionalneeds(needs);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post(EndPoints.CREATE_BOOKING)
                .then();

    }
    @Step
    public ValidatableResponse getBooking(int bookingId){
        return SerenityRest.given().log().all()
                .pathParam("bookingId",bookingId)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }
    @Step
    public ValidatableResponse updateBooking(int bookingId, String name, String lName,
                                               int price, boolean result, HashMap<String,String> dates, String needs){
        BookingPojo bookingPojo= new BookingPojo();
        bookingPojo.setFirstname(name);
        bookingPojo.setLastname(lName);
        bookingPojo.setTotalprice(price);
        bookingPojo.setDepositpaid(result);
        bookingPojo.setBookingdates(dates);
        bookingPojo.setAdditionalneeds(needs);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer e5b524a9808ba1")
                .header("admin","password123")
                .pathParam("bookingId",bookingId)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();

    }
    @Step
    public ValidatableResponse deleteBooking(int bookingId){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer e5b524a9808ba1")
                .header("admin","password123")
                .pathParam("bookingId",bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }

}
