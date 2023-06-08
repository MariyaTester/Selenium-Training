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

public class SortingGeoZonesTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final int countriesNamesColumn = 5;
    private final int countOfZonesColumn = 4;
    private final int zonesNamesColumn = 3;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Geo Zones | My Store"));
    }

    @Test
    public void checkSortingGeoZones() {
        List<WebElement> incomingCountriesWebElements = driver.findElements(By.className("row"));
        for (int i = 2; i <= incomingCountriesWebElements.size() + 1; i++) {
            String countOfGeoZones = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + i + "]/td[" +
                    countOfZonesColumn + "]")).getAttribute("textContent");
            int countOfZones = Integer.parseInt(countOfGeoZones);
            driver.findElement(
                    By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + i + "]/td[" +
                            countriesNamesColumn + "]")).click();
            wait.until(titleIs("Edit Geo Zone | My Store"));
            List<String> incomingZonesNames = new ArrayList<>();
            for (int j = 2; j <= countOfZones + 1; j++) {
                String zone = driver.findElement(
                        By.xpath("//*[@id=\"table-zones\"]/tbody/tr[" + j + "]/td[" +
                                zonesNamesColumn + "]/select")).getAttribute("textContent");
                incomingZonesNames.add(zone);
            }
            List<String> expectingZonesNames = new ArrayList<>(incomingZonesNames);
            Collections.sort(expectingZonesNames);
            assertEquals(expectingZonesNames, incomingZonesNames);
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
