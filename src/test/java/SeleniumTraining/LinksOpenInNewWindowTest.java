package SeleniumTraining;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class LinksOpenInNewWindowTest extends BaseTestClass {
    //@RepeatedTest(20)
    @Test
    public void clickOnLinks() {
        openAdminsMainPage();
        clickOnElement(By.xpath("//a[starts-with(@href, " +
                "'http://localhost/litecart/admin/?app=countries&doc=countries')]"));
        clickOnElement(By.xpath("//a[starts-with(@href," +
                "'http://localhost/litecart/admin/?app=countries&doc=edit_country')]"));
        String mainWindow = driver.getWindowHandle();
        int countOfLinks = driver.findElements(By.className("fa-external-link")).size();
        for (int i = 0; i < countOfLinks; i++) {
            driver.findElements(By.className("fa-external-link")).get(i).click();
            wait.until(numberOfWindowsToBe(2));
            Set<String> currentWindows = driver.getWindowHandles();
            currentWindows.remove(mainWindow);
            String newWindowHandle = currentWindows.iterator().next();
            driver.switchTo().window(newWindowHandle);
            driver.close();
            driver.switchTo().window(mainWindow);
            System.out.println(i);
        }
    }
}

