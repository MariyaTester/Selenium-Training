package SeleniumTraining;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ClickAllPointsOfTheAdminPanelTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    @Test
    public void clickAllPoints() {
        int sizeOfPointsList = driver.findElements(By.id("app-")).size();
        for (int i = 0; i < sizeOfPointsList; i++) {
            driver.findElements(By.id("app-")).get(i).click();
            int sizeOfSubPointsList = driver.findElements(By.cssSelector("[id^=doc]")).size();
            for (int j = 0; j < sizeOfSubPointsList; j++) {
                driver.findElements(By.cssSelector("[id^=doc]")).get(j).click();
                driver.findElement(By.tagName("h1"));
            }
        }
    }

        @AfterEach
        public void stop () {
            driver.quit();
            driver = null;
        }

    }
