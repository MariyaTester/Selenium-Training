package SeleniumTraining;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.assertj.core.api.SoftAssertions;


public class EveryProductHasStickerTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost/litecart/en/");

    }

    @Test
    public void clickAllProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector("[class$=hover-light]"));
        for (WebElement product : products) {
            assertEquals(1, product.findElements(By.cssSelector("[class^=sticker]")).size());
        }
    }
    @Test
    public void clickAllProductsWithSoftAssert() {
        SoftAssertions softAssertions = new SoftAssertions();
        List<WebElement> products = driver.findElements(By.cssSelector("[class$=hover-light]"));
        for (WebElement product : products) {
            softAssertions.assertThat(product.findElements(By.cssSelector("[class^=sticker]")).size()).isEqualTo(1);
        }
        softAssertions.assertAll();
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}