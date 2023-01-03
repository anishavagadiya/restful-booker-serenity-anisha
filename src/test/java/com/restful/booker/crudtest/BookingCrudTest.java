package com.restful.booker.crudtest;

import com.restful.booker.steps.BookingSteps;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class BookingCrudTest extends TestBase {
    static String name = "James" + TestUtils.getRandomValue();
    static String lastname = "Brown";
    static int price = 45;
    static boolean result = true;

    static HashMap<String, String> dates;
    static String needs = "Breakfast";
    static int bookingId;
    @Steps
    BookingSteps bookingSteps;

    @Title("Verify Booking was created ")
    @Test
    public void test001() {
        dates = new HashMap<>();
        dates.put("checkin", "2019-10-01");
        dates.put("checkout", "2019-20-01");
        ValidatableResponse response = bookingSteps.createBooking(name, lastname, price, result, dates, needs);
        response.log().all().statusCode(200);
        bookingId = response.log().all().extract().path("bookingid");

    }
    @Title("verify test get single booking")
    @Test
    public void test002(){
        ValidatableResponse response = bookingSteps.getBooking(bookingId);
        response.log().all().statusCode(200);
        response.body("firstname",equalTo(name));

    }
    @Title("Verify Booking was updated ")
    @Test
    public void test003() {
        name=name+"updated001";
        dates = new HashMap<>();
        dates.put("checkin", "2019-10-01");
        dates.put("checkout", "2019-20-01");
        ValidatableResponse response = bookingSteps.updateBooking(bookingId,name, lastname,price, result, dates, needs);
        HashMap<String, Object> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
       // response.log().all().statusCode(200);


    }
    @Title("verify booking was deleted")
    @Test
    public void test004(){
        ValidatableResponse response = bookingSteps.deleteBooking(bookingId);
        response.log().all().statusCode(200);
    }
}
