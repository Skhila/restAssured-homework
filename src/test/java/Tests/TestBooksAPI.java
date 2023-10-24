package Tests;

import Data.DataProviders;
import org.testng.annotations.Test;

import java.util.Map;

import static Data.CommonData.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class TestBooksAPI {

    @Test(dataProvider = "indexProvider", dataProviderClass = DataProviders.class)
    void booksTestWithoutParams(int index){
        extractedBooks.add(
            given()
            .when()
                    .get(bookStoreGetURLWithoutParams)
                    .jsonPath().getMap("books[" + index + "]")
        );
    }

    @Test(dataProvider = "ISBNProvider", dataProviderClass = DataProviders.class, dependsOnMethods = {"booksTestWithoutParams"})
    void booksTestWithParams(String isbn, String author){
        given()
                .param("ISBN", isbn)
        .when()
                .get(bookStoreGetURLWithParams)
        .then()
                .body("author", equalTo(author));

        System.out.printf("Author '%s' with ISBN '%s' Validated Successfully!\n" , author, isbn);
    }
}
