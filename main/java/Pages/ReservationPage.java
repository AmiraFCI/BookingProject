package Pages;

import Utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReservationPage {
    private final WebDriver driver;
    private final Helper helper;

    private final By bookingBoxHotelName = By.xpath("//h1[normalize-space()='Tolip Hotel Alexandria']");
   private final By checkindate=By.xpath("//div[normalize-space()='Wed 1 Oct 2025']");
    private final By checkoutdate=By.xpath("//div[normalize-space()='Tue 14 Oct 2025']");
    public ReservationPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper(driver);
    }

    public String getHotelNameFromBookingBox() {
        if (helper.isDisplayed(bookingBoxHotelName)) {
            return helper.getText(bookingBoxHotelName);
        } else if (helper.isDisplayed(checkindate)) {
            return helper.getText(checkoutdate);
        }
        return "";
    }
    public String getcheckinDate()
    {
    return  helper.getText(checkindate);
    }
    public String getcheckoutDate()
    {
        return  helper.getText(checkoutdate);
    }


    public boolean verifyHotelName(String expectedHotelName) {
        helper.waitForVisible(bookingBoxHotelName);
        String actualHotelName = driver.findElement(bookingBoxHotelName).getText().trim();
        return actualHotelName.equalsIgnoreCase(expectedHotelName);
    }
    public boolean verifycheckin(String expectedlcheckin){
        helper.waitForVisible(checkindate);
        String actualcheckin= driver.findElement(checkindate).getText().trim();
        return actualcheckin.equalsIgnoreCase(expectedlcheckin);
    }
    public boolean verifycheckout(String expectedlcheckout){
        helper.waitForVisible(checkindate);
        String actualcheckout= driver.findElement(checkindate).getText().trim();
        return actualcheckout.equalsIgnoreCase(expectedlcheckout);
    }
    }

