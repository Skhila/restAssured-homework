package Steps;

import SpecBuilders.RequestSpecs;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import models.request.Booking;
import models.response.BookingResponseFromGet;

import static Data.CommonData.bookingURI;
import static SpecBuilders.ResponseSpecs.createResponseCheckerSpec;
import static io.restassured.RestAssured.given;


public class UpdateBookingSteps {
    RequestSpecification requestSpecForBookerUpdate;
    public UpdateBookingSteps(){
        requestSpecForBookerUpdate = RequestSpecs.getRequestSpecForBookerUpdate();
    }

    @Step("Update Booking By ID")
    public BookingResponseFromGet updateBookingByID(int id, Booking bookingForUpdate){
        BookingResponseFromGet updatedBooking =
                requestSpecForBookerUpdate
                        .body(bookingForUpdate)
                .when()
                        .put(bookingURI + id)
                .then()
                        .spec(createResponseCheckerSpec(200))
                        .extract().body().as(BookingResponseFromGet.class);

        return updatedBooking;
    }
}
