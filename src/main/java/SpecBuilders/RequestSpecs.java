package SpecBuilders;

import Data.CommonData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.request.Booking;

import static io.restassured.RestAssured.given;

public class RequestSpecs {

    public static RequestSpecification getRequestSpecForBooker(String username, String password){
        RequestSpecification requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(CommonData.bookerBaseURI)
                .setContentType(ContentType.JSON)
                .build();

        return given(requestSpecBuilder).auth().preemptive().basic(username, password);
    }

    public static RequestSpecification getBaseRequestSpecForBooking(){
        return new RequestSpecBuilder()
                .setBaseUri(CommonData.bookerBaseURI)
                .setContentType(ContentType.JSON)
                .build();
    }
}
