package Pages;

import Utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeoutException;

public class DetailsPage    {
    private final WebDriver driver;
    private final Helper helper;
    private final By hotelName =  By.xpath("//div[normalize-space()='Tolip Hotel Alexandria']");

    private final By dateRange = By.cssSelector("div[data-testid='date-display-field'], div.bui-u-inline");
    private final By roomCard = By.cssSelector("div[data-room-id], div.hp-room-wrapper");
    private final By reserveButton = By.xpath("//button[@id='hp_book_now_button']");
    private final By selectAmountDropdown=By.xpath("//select[@id='hprt_nos_select_78883120_386871369_0_33_0_131741']");


    public DetailsPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper(driver);
    }

    public String getDisplayedHotelName() {
        return helper.getText(hotelName);
    }

    public String getDisplayedDateRange() {
        return helper.getText(dateRange);
    }

    public void selectFirstAvailableRoom() {
        helper.waitForVisible(roomCard);
        WebElement firstRoom = driver.findElement(roomCard);
        helper.selectFirstOption(selectAmountDropdown);
    }

    public void clickIllReserve() {
        helper.click(reserveButton);
    }

}
