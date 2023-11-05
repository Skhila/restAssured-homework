package SpecBuilders;

import Data.CommonData;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.request.Booking;

import static Data.CommonData.basicAuthString;
import static io.restassured.RestAssured.given;

public class RequestSpecs {

    public static RequestSpecification getRequestSpecForBookerUpdate(){
        RequestSpecification requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(CommonData.bookerBaseURI)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();

        return given(requestSpecBuilder).header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    public static RequestSpecification getBaseRequestSpecForBooking(){
        return new RequestSpecBuilder()
                .setBaseUri(CommonData.bookerBaseURI)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }
}
