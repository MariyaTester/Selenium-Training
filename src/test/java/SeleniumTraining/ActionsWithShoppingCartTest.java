package SeleniumTraining;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ActionsWithShoppingCartTest extends BaseTestClass {
    //@RepeatedTest(100)

    @Test
    public void addAndDeleteProducts() {
        openClientsMainPage();
        for (int i = 1; i <= 3; i++) {
            clickOnElement(By.className("product"));
            if (driver.findElements(By.name("options[Size]")).size() != 0) {
                clickOnElement(By.name("options[Size]"));
                clickOnElement(By.xpath("//select[@name='options[Size]']//option[@value='Small']"));
            }
            clickOnElement(By.name("add_cart_product"));
            WebElement counter = driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']"));
            wait.until(textToBePresentInElement(counter, String.valueOf(i)));
            openClientsMainPage();

        }
        clickOnElement(By.id("cart"));
        int countOfDifferentProducts = driver.findElements(By.className("shortcut")).size();
        for (int i = countOfDifferentProducts; i > 1; i--) {
            System.out.println("Удаление товарва " + i + " из " + countOfDifferentProducts);
            WebElement deletingElement = driver.findElement(By.className("shortcut"));
            clickOnElement(By.name("remove_cart_item"));
            wait.until(ExpectedConditions.stalenessOf(deletingElement));
        }
        System.out.println("Удаление последнего товара");
        clickOnElement(By.name("remove_cart_item"));
    }
}