package Steps;

import SpecBuilders.ResponseSpecs;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;

import static Data.CommonData.*;
import static SpecBuilders.RequestSpecs.*;
import static io.restassured.RestAssured.*;

public class BookerSteps {
    private String username;
    private String password;

    RequestSpecification bookingRequestSpecification;
    ResponseSpecification bookingResponseSpecification;

    public BookerSteps(String username, String password){
        this.username = username;
        this.password = password;

        bookingRequestSpecification = getRequestSpecForBooker(this.username, this.password);
        bookingResponseSpecification = ResponseSpecs.createDeleteResponseSpec();
    }

    // JSONObjects For Booker Body Data
    public static JSONObject bodyParams = new JSONObject(),
            bookingDates = new JSONObject();

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

    @Step
    public int getBookingID(){
        return given(bookingRequestSpecification)
                .get(bookingURI)
                .jsonPath().getInt("[0].bookingid");
    }

    @Step("Delete Random Booking")
    public BookerSteps deleteBookingByID(int bookingID){
        int deleteResponseStatusCode = given(bookingRequestSpecification)
                .when()
                    .delete(bookingURI + bookingID)
                .then()
                    .spec(bookingResponseSpecification)
                    .extract().statusCode();

        System.out.printf("Booking(ID = %d) Deleted Successfully(Status Code = %d)\n", bookingID, deleteResponseStatusCode);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
