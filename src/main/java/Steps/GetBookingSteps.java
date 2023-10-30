package Steps;

import Data.CommonData;
import SpecBuilders.RequestSpecs;
import SpecBuilders.ResponseSpecs;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import models.request.Booking;
import models.response.BookingResponseFromGet;


import static SpecBuilders.ResponseSpecs.createResponseCheckerSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetBookingSteps {
    RequestSpecification baseRequestSpecification;
    public GetBookingSteps(){
        baseRequestSpecification = RequestSpecs.getBaseRequestSpecForBooking();
    }

    @Step("Get Booking By ID")
    public BookingResponseFromGet getBookingByID(int id){
        return
                given(baseRequestSpecification)
                .when()
                        .get(CommonData.bookingURI + id)
                .then()
                        .spec(createResponseCheckerSpec(200))
                .extract().body().as(BookingResponseFromGet.class);
    }

    @Step("Validate Booking")
    public GetBookingSteps validateBooking(Booking actualBooking, BookingResponseFromGet expectedBooking){
        assertThat(actualBooking.firstname, equalTo(expectedBooking.firstname));
        assertThat(actualBooking.lastname, equalTo(expectedBooking.lastname));
        assertThat(actualBooking.totalprice, equalTo(expectedBooking.totalprice));
        assertThat(actualBooking.depositpaid, equalTo(expectedBooking.depositpaid));
        assertThat(actualBooking.bookingdates.checkin, equalTo(expectedBooking.bookingdates.checkin));
        assertThat(actualBooking.bookingdates.checkout, equalTo(expectedBooking.bookingdates.checkout));
        assertThat(actualBooking.additionalneeds, equalTo(expectedBooking.additionalneeds));

        System.out.println("Booking Validated Successfully! 🥳");
        return this;
    }
}
