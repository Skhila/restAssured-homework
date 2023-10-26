package Data;

public class CommonData {

    // Booker Authentication Data
    public static final String bookStoreGetBooksURI = "https://bookstore.toolsqa.com/BookStore/v1/Books",
            bookerAuthURI = "https://restful-booker.herokuapp.com/auth",
            firstBookerUpdateURI = "https://restful-booker.herokuapp.com/booking/1";

    // Booker Data
    public static final String userName = "admin",
            userPassword = "password123",
            bookerFirstName = "Jack",
            bookerLastName = "Reacher",
            bookerCheckinDate = "2023-03-03",
            bookerCheckoutDate = "2023-11-11",
            bookerAdditionalNeeds = "Rifle";
    public static final int bookerTotalPrice = 911;
    public static final boolean bookerDepositPaid = false;

    // Books Data
    public static final String expectedAuthorOfFirstBook = "Richard E. Silverman",
            expectedAuthorOfSecondBook = "Addy Osmani";

}