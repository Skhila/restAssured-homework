package Steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static Data.CommonData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookStoreSteps {
    Response bookStoreResponse;

    @Step("Get Response From Books Api")
    public BookStoreSteps getBookStoreResponse(){
        bookStoreResponse =
                given()
                .when()
                        .get(bookStoreGetBooksURI);

        return this;
    }

    @Step("Check Pages Of All Books")
    public BookStoreSteps checkPagesOfAllBooks(){
        bookStoreResponse
                .then()
                    .body("books.pages", everyItem(lessThan(1000)));

        System.out.println("Books Pages Validated Successfully!");

        return this;
    }

    @Step("Check Authors Of First And Second Books")
    public BookStoreSteps checkAuthorsOfFirstTwoBooks(){
        bookStoreResponse
                .then()
                    .body("books[0,1].author", contains(expectedAuthorOfFirstBook, expectedAuthorOfSecondBook));

        System.out.println("Authors Validated Successfully!");
        return this;
    }

}
