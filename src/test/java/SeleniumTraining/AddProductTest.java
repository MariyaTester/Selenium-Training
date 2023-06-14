package SeleniumTraining;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AddProductTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String productName = "New Duck " + getNumber();

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void addingProduct() {
        log("Вход в каталог");
        clickOnElement(By.xpath("//a[starts-with(@href," +
                "'http://localhost/litecart/admin/?app=catalog&doc=catalog')]"));
        log("Нажатие кнопки добавления товара");
        clickOnElement(By.xpath("//a[starts-with(@href," +
                "'http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product')]"));

        log("Заполнение полей вкладки General");
        clickOnElement(By.xpath("//*[@value=1]"));
        typeTextInElement(By.name("name[en]"), productName);
        typeTextInElement(By.name("code"), "ND");
        clickOnElement(By.xpath("//*[@value='1-3']"));
        clearElement(By.name("quantity"));
        typeTextInElement(By.name("quantity"), "1000");
        File product = new File("./src/test/resources/gus.jpg");
        typeTextInElement(By.name("new_images[]"), product.getAbsolutePath());
        typeTextInElement(By.name("date_valid_from"), "13062013");
        typeTextInElement(By.name("date_valid_to"), "09102024");

        log("Переход на вкладку Information");
        clickOnElement(By.xpath("//a[starts-with(@href, '#tab-information')]"));

        log("Заполнение полей вкладки Information");
        clickOnElement(By.name("manufacturer_id"));
        clickOnElement(By.xpath("//select[@name='manufacturer_id']//option[@value='1']"));
        typeTextInElement(By.name("keywords"), "Duck, New, Goose");
        typeTextInElement(By.name("short_description[en]"), "Duck (goose) anti-stress for your wonderful mood");
        typeTextInElement(By.xpath("//*[@contenteditable='true']"),
                "Cool antistress toy, in the form of a duck. " +
                        "When you press it, the eyes pop out of their orbits. " +
                        "Please yourself and your loved ones such a cool toy.");
        typeTextInElement(By.name("head_title[en]"), "New Duck");
        typeTextInElement(By.name("meta_description[en]"), "New Duck");

        log("Переход на вкладку Prices");
        clickOnElement(By.xpath("//a[starts-with(@href, '#tab-prices')]"));

        log("Заполнение полей вкладки Prices");
        clearElement(By.name("purchase_price"));
        typeTextInElement(By.name("purchase_price"), "5");
        clickOnElement(By.name("purchase_price_currency_code"));
        clickOnElement(By.xpath("//select[@name='purchase_price_currency_code']//option[@value='EUR']"));
        typeTextInElement(By.name("prices[USD]"), "4");
        typeTextInElement(By.name("prices[EUR]"), "5");

        log("Сохранение введенных данных (добавление товара)");
        clickOnElement(By.name("save"));

        log("Проверка отображения добавленного товара в каталоге (админская часть)");
        clickOnElement(By.linkText(productName));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

    private void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    private void typeTextInElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    private void clearElement(By by) {
        driver.findElement(by).clear();
    }

    private String getNumber() {
        return String.valueOf(1 + (int) (Math.random() * 9999));
    }

    private void log(String logInfo) {
        System.out.println(logInfo);
    }
}