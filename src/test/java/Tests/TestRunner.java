package Tests;

import Steps.BookStoreSteps;
import Steps.BookerSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestRunner {
    BookerSteps bookerSteps;
    BookStoreSteps bookStoreSteps;

    @BeforeClass
    public void initiateSteps(){
        bookerSteps = new BookerSteps();
        bookStoreSteps = new BookStoreSteps();
    }
    @Test
    public void testBookerAPI(){
        bookerSteps
                .setRequestUser()
                .setRequestBodyData()
                .updateBookerData(bookerSteps.getToken());
    }

    @Test
    public void testBooksAPI(){
        bookStoreSteps
                .getBookStoreResponse()
                .checkPagesOfAllBooks()
                .checkAuthorsOfFirstTwoBooks();
    }
}
