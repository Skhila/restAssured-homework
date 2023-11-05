package Data;

import org.testng.annotations.DataProvider;

import static Data.CommonData.*;

public class DataProviders {
    @DataProvider(name = "bookingDataProvider")
    public static Object[][] bookingDataProvider(){
        Object[] firstBooking = {bookingFirstname1, bookingLastname1, bookingCheckinDate1,
                bookingCheckoutDate1, bookingAdditionalNeeds1, bookingPassportNo1, bookingTotalPrice1,
                bookingSalePrice1, bookingDepositPaid1};
        Object[] secondBooking = {bookingFirstname2, bookingLastname2, bookingCheckinDate2,
                bookingCheckoutDate2, bookingAdditionalNeeds2, bookingPassportNo2, bookingTotalPrice2,
                bookingSalePrice2, bookingDepositPaid2};
        return new Object[][]{
                firstBooking,
                secondBooking
        };
    }
}
