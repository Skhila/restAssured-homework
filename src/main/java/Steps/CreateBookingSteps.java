package Steps;

import io.qameta.allure.Step;
import models.request.Booking;
import models.request.BookingDates;

import static io.restassured.RestAssured.given;

public class CreateBookingSteps {

    @Step("Create Booking Dates For Booking Body")
    public BookingDates createBookingDates(String checkin, String checkout){
        BookingDates bookingDates = new BookingDates(checkin, checkout);

        return bookingDates;
    }

    @Step("Create Booking Body For POST Request")
    public Booking createBookingBody(String firstName, String lastName, int totalPrice, boolean depositPaid, BookingDates bookingDates, String additionalNeeds, int salePrice, String passportNo){
        Booking booking = new Booking();
        booking.setFirstName(firstName);
        booking.setLastName(lastName);
        booking.setTotalPrice(totalPrice);
        booking.setDepositPaid(depositPaid);
        booking.setBookingDates(bookingDates);
        booking.setAdditionalNeeds(additionalNeeds);
        booking.setSalePrice(salePrice);
        booking.setPassportNo(passportNo);

        return booking;
    }
}
