package Steps;

import io.qameta.allure.Step;
import org.json.simple.JSONObject;

import static Data.CommonData.*;
import static io.restassured.RestAssured.given;

public class BookerSteps {

    // Booker Data JSONObjects
    public static JSONObject bodyParams = new JSONObject(),
            bookingDates = new JSONObject(),
            requestUser = new JSONObject();

    @Step("Set Request User And Password To get Token")
    public BookerSteps setRequestUser(){
        requestUser.put("username", userName);
        requestUser.put("password", userPassword);
        return this;
    }

    @Step("Get Token For Further Use")
    public String getToken(){
        return given()
                        .contentType("application/json")
                        .body(requestUser.toJSONString())
                        .when()
                        .post(bookerAuthURI)
                        .jsonPath().getString("token");
    }

    @Step("Sets Request Body Data To Update Booker Data")
    public BookerSteps setRequestBodyData(){
        bodyParams.put("firstname", bookerFirstName);
        bodyParams.put("lastname", bookerLastName);
        bodyParams.put("totalprice", bookerTotalPrice);
        bodyParams.put("depositpaid", false);

        bookingDates.put("checkin", bookerCheckinDate);
        bookingDates.put("checkout", bookerCheckoutDate);

        bodyParams.put("bookingdates", bookingDates);
        bodyParams.put("additionalneeds", bookerAdditionalNeeds);

        return this;
    }

    @Step("Sends Put Request To Update First Booker's Data")
    public BookerSteps updateBookerData(String token){
        int putResponse =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .header("Cookie", "token=" + token)
                        .body(bodyParams.toJSONString())
                .when()
                        .put(firstBookerUpdateURI)
                .then()
                        .log().ifStatusCodeIsEqualTo(201)
                        .extract().statusCode();

        System.out.println("Status Code Of The Recent Put Request: " + putResponse);
        return this;
    }
}
