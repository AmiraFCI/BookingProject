package Pages;

import Utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private final WebDriver driver;
    private final Helper helper;

    private final By searchBox = By.xpath("//input[@id=':rh:']");
    private final By searchButton = By.xpath("//span[normalize-space()='Search']");

    private final String dateSelectorPattern = "td[data-date='%s']";
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper(driver);
    }

    public void searchForLocationAndDates(String location, String checkIn, String checkOut) {
        // Type location
        helper.type(searchBox, location);

        // Pick check-in date
        By checkInDate = By.cssSelector(String.format(dateSelectorPattern, checkIn));
        helper.click(checkInDate);

        // Pick check-out date
        By checkOutDate = By.cssSelector(String.format(dateSelectorPattern, checkOut));
        helper.click(checkOutDate);

        // Click search
        helper.click(searchButton);
    }



}
