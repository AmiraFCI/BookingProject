package Pages;

import Utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {
    private final WebDriver driver;
    private Helper helper;

    private final By hotelCards = By.xpath("//div[normalize-space()='Tolip Hotel Alexandria']");

    private final String hotelNameXpath = ".//div[contains(@class,'fcab3ed991') ";

    private final By availability = By.xpath(".//a[contains(.,'See availability')]");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        this.helper = helper;
    }
    public void selectHotelByName(String hotelName) {
        By hotelCard = By.xpath("//div[@data-testid='property-card']//div[contains(text(),'" + hotelName + "')]");
        helper.scrollIntoView(hotelCard);
        helper.click(hotelCard);}

    public void waitForResultsToLoad() {
        helper.waitForVisible(hotelCards);
    }

    public boolean clickSeeAvailabilityForHotel(String hotelName, int maxPages) {
        for (int page = 1; page <= maxPages; page++) {
            helper.waitForVisible(hotelCards);
            List<WebElement> cards = driver.findElements(hotelCards);

            for (WebElement card : cards) {
                String nameText = "";
                try {
                    WebElement nameEl = card.findElement(By.xpath(hotelNameXpath));
                    nameText = nameEl.getText().trim();
                } catch (Exception ignored) {
                }

                if (nameText.toLowerCase().contains(hotelName.toLowerCase())) {
                    try {
                        WebElement seeBtn = card.findElement(availability);
                        helper.scrollIntoView(availability);
                        seeBtn.click();
                    } catch (Exception e) {
                        try {
                            WebElement link = card.findElement(availability);
                            helper.scrollIntoView(availability);
                            link.click();
                        } catch (Exception ex) {
                            try {
                                WebElement hotelLink = card.findElement(By.xpath(".//a[contains(@href,'hotel')]"));
                                helper.scrollIntoView(By.xpath(".//a[contains(@href,'hotel')]"));
                                hotelLink.click();
                            } catch (Exception ignored2) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }

        }
        return true;
    }
}