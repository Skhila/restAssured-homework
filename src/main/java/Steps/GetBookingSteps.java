package Steps;

import Data.CommonData;
import SpecBuilders.RequestSpecs;
import SpecBuilders.ResponseSpecs;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import models.request.Booking;
import models.response.BookingResponseFromGet;


import static Data.CommonData.bookingURI;
import static SpecBuilders.RequestSpecs.getBaseRequestSpecForBooking;
import static SpecBuilders.ResponseSpecs.createResponseCheckerSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetBookingSteps {
    RequestSpecification baseRequestSpecification;
    public GetBookingSteps(){
        baseRequestSpecification = getBaseRequestSpecForBooking();
    }

    @Step("Get Booking By ID")
    public BookingResponseFromGet getBookingByID(int id){
        return
                given(baseRequestSpecification)
                .when()
                        .get(bookingURI + id)
                .then()
                        .spec(createResponseCheckerSpec(200))
                .extract().body().as(BookingResponseFromGet.class);
    }

    @Step("Get Random Booking ID")
    public int getBookingID(){
        return given(getBaseRequestSpecForBooking())
                .get(bookingURI)
                .jsonPath().getInt("[0].bookingid");
    }
}
