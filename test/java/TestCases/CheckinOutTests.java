package TestCases;

import Pages.DetailsPage;
import Pages.HomePage;
import Pages.ReservationPage;
import Pages.SearchResultPage;
import Utils.Helper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckinOutTests extends BaseTest{




    @Test(dataProvider = "datesData", dataProviderClass = DataProvider.class)
    public void verifyDatesOnHotelDetailsPage(String location, String checkIn, String checkOut, String hotelName) {


        HomePage home = new HomePage(driver);
        SearchResultPage results = new SearchResultPage(driver);
        DetailsPage details = new DetailsPage(driver);
        Helper helper = new Helper(driver);

        home.searchForLocationAndDates(location, checkIn, checkOut);
        results.waitForResultsToLoad();
        results.selectHotelByName(hotelName);
        helper.switchToNewTab();

        Assert.assertTrue(
               new  ReservationPage(driver).verifycheckin(checkIn),
                "Displayed check-in check-out dates are incorrect!"
        );
        Assert.assertTrue(
                new  ReservationPage(driver).verifycheckin(checkOut),
                "Displayed check-out dates are incorrect!"
        );


    }
}