package TestCases;

import Pages.DetailsPage;
import Pages.HomePage;
import Pages.ReservationPage;
import Pages.SearchResultPage;
import Utils.Helper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HotelNametest extends BaseTest{
    @Test(dataProvider = "hotelData", dataProviderClass = DataProvider.class)
    public void verifyHotelNameOnReservationPage(String location, String checkIn, String checkOut, String hotelName) {


        HomePage home = new HomePage(driver);
        SearchResultPage results = new SearchResultPage(driver);
        DetailsPage details = new DetailsPage(driver);
        ReservationPage reservation = new ReservationPage(driver);
        Helper helper = new Helper(driver);


        home.searchForLocationAndDates(location, checkIn, checkOut);
        results.waitForResultsToLoad();
        results.selectHotelByName(hotelName);
        helper.switchToNewTab();

        details.selectFirstAvailableRoom();
        details.clickIllReserve();


        Assert.assertTrue(
                reservation.verifyHotelName(hotelName),
                "Hotel name on reservation page is incorrect!"
        );
    }
}
