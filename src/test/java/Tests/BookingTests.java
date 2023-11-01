package Tests;

import Data.DataProviders;
import Steps.CreateBookingSteps;
import Steps.GetBookingSteps;
import Steps.UpdateBookingSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.request.Booking;
import models.request.BookingDates;
import models.response.BookingResponseFromGet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Epic("API Tests")
@Feature("Booking API Tests Using RestAssured")
public class BookingTests {
    CreateBookingSteps createBookingSteps;
    GetBookingSteps getBookingSteps;
    UpdateBookingSteps updateBookingSteps;

    @BeforeClass
    public void initiateSteps() {
        createBookingSteps = new CreateBookingSteps();
        getBookingSteps = new GetBookingSteps();
        updateBookingSteps = new UpdateBookingSteps();
    }

    @Test(dataProvider = "bookingDataProvider", dataProviderClass = DataProviders.class)
    public void updateBookingTest(String firstname, String lastname, String checkinDate, String checkoutDate, String additionalNeeds,
                                  String passportNo, int totalPrice, int salePrice, boolean depositPaid){
        int bookingID = getBookingSteps.getBookingID();

        BookingDates bookingDates = createBookingSteps.createBookingDates(checkinDate, checkoutDate);
        Booking bookingBodyForUpdate = createBookingSteps.createBookingBody(firstname, lastname, totalPrice, depositPaid,
                bookingDates, additionalNeeds, salePrice, passportNo);

        BookingResponseFromGet bookingBeforeUpdate = getBookingSteps.getBookingByID(bookingID);

        BookingResponseFromGet bookingAfterUpdate = updateBookingSteps.updateBookingByID(bookingID, bookingBodyForUpdate);

        assertThat(bookingAfterUpdate, not(equalTo(bookingBeforeUpdate)));
        System.out.println("Update Validated Successfully! 🥳");
    }
}
