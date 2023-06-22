package SeleniumTraining;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTestClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String mainClientsPageURL = "http://localhost/litecart/";
    protected final String mainAdminsPageURL = "http://localhost/litecart/admin/";

    @BeforeEach
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

    protected void openClientsMainPage() {
        driver.get(mainClientsPageURL);
    }

    protected void openAdminsMainPage() {
        driver.get(mainAdminsPageURL);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        clickOnElement(By.name("login"));
    }

    protected void clickOnElement(By by) {
        driver.findElement(by).click();
    }
}
