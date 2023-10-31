package Steps;

import Data.CommonData;
import SpecBuilders.RequestSpecs;
import SpecBuilders.ResponseSpecs;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import models.request.Booking;
import models.request.BookingDates;
import models.response.BookingResponseFromPost;

import static SpecBuilders.RequestSpecs.getBaseRequestSpecForBooking;
import static SpecBuilders.ResponseSpecs.createResponseCheckerSpec;
import static io.restassured.RestAssured.given;

public class CreateBookingSteps {
    Booking booking;

    @Step("Create Booking Dates For Booking Body")
    public BookingDates createBookingDates(String checkin, String checkout){
        BookingDates bookingDates = new BookingDates();
        bookingDates.checkin = checkin;
        bookingDates.checkout = checkout;

        return bookingDates;
    }

    @Step("Create Booking Body For POST Request")
    public CreateBookingSteps createBookingBody(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingDates, String additionalneeds){
        booking = new Booking();
        booking.firstname = firstname;
        booking.lastname = lastname;
        booking.totalprice = totalprice;
        booking.depositpaid = depositpaid;
        booking.bookingdates = bookingDates;
        booking.additionalneeds = additionalneeds;

        return this;
    }

    @Step("Create Booking Using POST Request")
    public BookingResponseFromPost createBooking(){
        BookingResponseFromPost response =
                given(getBaseRequestSpecForBooking())
                        .body(booking)
                .when()
                        .post(CommonData.bookingURI)
                .then()
                        .spec(createResponseCheckerSpec(200))
                .extract().body().as(BookingResponseFromPost.class);

        System.out.println("Booking Created Successfully! 🥳");
        return response;
    }
}
