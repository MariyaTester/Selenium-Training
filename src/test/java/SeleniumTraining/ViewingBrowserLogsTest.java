package SeleniumTraining;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

public class ViewingBrowserLogsTest extends BaseTestClass {
    @Test
    public void clickOnProducts() {
        String directoryWithProductsURL = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";
        openAdminsMainPage();
        clickOnElement(By.xpath("//a[starts-with(@href, " +
                "'http://localhost/litecart/admin/?app=catalog&doc=catalog')]"));
        clickOnElement(By.xpath("//a[starts-with(@href, '" + directoryWithProductsURL + "')]"));
        List<WebElement> productsWithEditLinks = driver.findElements(By.xpath("//a[starts-with(@href, " +
                "'http://localhost/litecart/admin/?app=catalog&doc=edit_product&category_id=1&product')]"));
        for (int i = 0; i < productsWithEditLinks.size(); i++) {
            productsWithEditLinks = driver.findElements(By.xpath("//a[starts-with(@href, " +
                    "'http://localhost/litecart/admin/?app=catalog&doc=edit_product&category_id=1&product')]"));
            if (!productsWithEditLinks.get(i).getAttribute("title").contains("Edit")) {
                productsWithEditLinks.get(i).click();
                List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
                softAssertions.assertThat(logs.size()).isEqualTo(0);
                driver.get(directoryWithProductsURL);
            }
        }
        softAssertions.assertAll();
    }
}
