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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SortingCountriesAndGeoZonesTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final int countriesNamesColumn = 5;
    private final int editColumn = 7;
    private final int zonesNamesColumn = 3;
    private final int countOfZonesColumn = 6;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Countries | My Store"));
    }

    @Test
    public void checkSortingCountriesNames() {
        List<WebElement> incomingCountriesNamesWebElements = driver.findElements(By.className("row"));
        List<String> incomingCountriesNames = new ArrayList<>();
        for (int i = 2; i <= incomingCountriesNamesWebElements.size() + 1; i++) {
            String country = driver.findElement(
                    By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + i + "]/td[" +
                            countriesNamesColumn + "]")).getAttribute("textContent");
            incomingCountriesNames.add(country);
        }
        List<String> expectingCountriesNames = incomingCountriesNames;
        Collections.sort(expectingCountriesNames);
        assertEquals(expectingCountriesNames, incomingCountriesNames);
    }

    @Test
    public void checkSortingGeoZonesNames() {
        List<WebElement> incomingCountriesNamesWebElements = driver.findElements(By.className("row"));
        for (int i = 2; i <= incomingCountriesNamesWebElements.size() + 1; i++) {
            String zones = driver.findElement(
                    By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + i + "]/td[" +
                            countOfZonesColumn + "]")).getAttribute("textContent");
            int countOfZones = Integer.parseInt(zones);
            if (countOfZones > 0) {
                driver.findElement(
                        By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + i + "]/td[" +
                                editColumn + "]")).click();
                wait.until(titleIs("Edit Country | My Store"));
                List<WebElement> incomingZonesNamesWebElements = driver.findElements(By.className("row"));
                List<String> incomingZonesNames = new ArrayList<>();
                for (int j = 2; j <= incomingZonesNamesWebElements.size() + 1; j++) {
                    String zone = driver.findElement(
                            By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + j + "]/td[" +
                                    zonesNamesColumn + "]")).getAttribute("textContent");
                    incomingZonesNames.add(zone);
                }
                List<String> expectingZonesNames = incomingZonesNames;
                Collections.sort(expectingZonesNames);
                assertEquals(expectingZonesNames, incomingZonesNames);
                driver.navigate().back();
            }
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

}
