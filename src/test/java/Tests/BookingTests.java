package Tests;

import Steps.CreateBookingSteps;
import Steps.GetBookingSteps;
import models.request.BookingDates;
import models.response.BookingResponseFromGet;
import models.response.BookingResponseFromPost;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Data.CommonData.*;

public class BookingTests {
    CreateBookingSteps createBookingSteps;
    GetBookingSteps getBookingSteps;
    BookingResponseFromPost bookingResponseFromPost;

    @BeforeClass
    public void initiateSteps() {
        createBookingSteps = new CreateBookingSteps();
        getBookingSteps = new GetBookingSteps();
    }

    @Test
    public void createBookingTest() {
        BookingDates bookingDates = createBookingSteps.createBookingDates(bookingCheckinDate, bookingCheckoutDate);

        bookingResponseFromPost = createBookingSteps
                .createBookingBody(bookingFirstname, bookingLastname, bookingTotalPrice, bookingDepositPaid, bookingDates, bookingAdditionalNeeds)
                .createBooking();

    }

    @Test
    public void getCreatedBookingTest(){
        BookingResponseFromGet bookingResponseFromGet = getBookingSteps.getBookingByID(bookingResponseFromPost.bookingid);

        getBookingSteps.validateBooking(bookingResponseFromPost.booking, bookingResponseFromGet);
    }
}
