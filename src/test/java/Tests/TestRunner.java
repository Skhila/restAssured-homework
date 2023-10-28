package Tests;

import Steps.BookStoreSteps;
import Steps.BookerSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Data.CommonData.userName;
import static Data.CommonData.userPassword;

public class TestRunner {
    BookerSteps bookerSteps;
    BookStoreSteps bookStoreSteps;

    @BeforeClass
    public void initiateSteps(){
        bookerSteps = new BookerSteps(userName, userPassword);
        bookStoreSteps = new BookStoreSteps();
    }
    @Test
    public void testBookerAPI(){
        int bookingID = bookerSteps.getBookingID();

        bookerSteps
                .setRequestBodyData()
                .deleteBookingByID(bookingID);
    }

    @Test
    public void testBooksAPI(){
        bookStoreSteps
                .getBookStoreResponse()
                .checkPagesOfAllBooks()
                .checkAuthorsOfFirstTwoBooks();
    }
}
