package Data;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Data.CommonData.extractedBooks;

public class DataProviders {

    @DataProvider(name = "indexProvider")
    public static Object[][] indexProvider(){
        return new Object[][]{
                {0},
                {1}
        };
    }

    @DataProvider(name = "ISBNProvider")
    public static Object[][] ISBNProvider(){
        return new Object[][]{
                {extractedBooks.get(0).get("isbn"), extractedBooks.get(0).get("author")},
                {extractedBooks.get(1).get("isbn"), extractedBooks.get(1).get("author")}
        };
    }
}
