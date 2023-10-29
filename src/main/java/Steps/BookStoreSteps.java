package Steps;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static Data.CommonData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookStoreSteps {
    JsonPath bookStoreResponse;

    @Step("Get Response From Books Api")
    public BookStoreSteps getBookStoreResponse(){
        bookStoreResponse =
                given()
                .when()
                        .get(bookStoreGetBooksURI)
                .then().extract().jsonPath();

        return this;
    }

    @Step("Check Pages Of All Books")
    public BookStoreSteps checkPagesOfAllBooks(){
        List<Integer> booksPages = bookStoreResponse.getList("books.pages");
        assertThat(booksPages, everyItem(lessThan(expectedMaxPageCount)));

        System.out.println("Pages Validated Successfully!");
        return this;
    }

    @Step("Check Authors Of First And Second Books")
    public BookStoreSteps checkAuthorsOfFirstTwoBooks() {
        List<String> firstTwoBooksAuthors = bookStoreResponse.getList("books[0,1].author");
        assertThat(firstTwoBooksAuthors, contains(expectedAuthorOfFirstBook, expectedAuthorOfSecondBook));

        System.out.println("Authors Validated Successfully!");
        return this;
    }
}
