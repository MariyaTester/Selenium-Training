package SeleniumTraining;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class RegistrationTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String login = getStringOfLetters() + "@gmail.com";
    private final String password = getStringOfLetters();
    private final String country = "United States\n";
    private final String loginXPath = "//a[starts-with(@href,'http://localhost/litecart/en/create_account')]";
    private final String logOutXPath = "//a[starts-with(@href,'http://localhost/litecart/en/logout')]";
    private final String phoneRegionCode = "310";

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost/litecart/en/");
    }

    @Test
    public void registration() {
        log("Переход в меню регистрации");
        driver.findElement(By.xpath(loginXPath)).click();
        log("Заполнение имени пользователя");
        driver.findElement(By.name("firstname")).sendKeys(getStringOfLetters());
        log("Заполнение фамилии пользователя");
        driver.findElement(By.name("lastname")).sendKeys(getStringOfLetters());
        log("Заполнение адреса (адрес 1)");
        driver.findElement(By.name("address1")).sendKeys(getStringOfLetters() + getNumber());
        log("Заполнение почтового кода (США)");
        driver.findElement(By.name("postcode")).sendKeys(getNumber());
        log("Заполнение поля города");
        driver.findElement(By.name("city")).sendKeys(getStringOfLetters());
        log("Выбор страны (United States)");
        driver.findElement(By.className("select2-selection__rendered")).click();
        driver.findElement(By.className("select2-search__field")).sendKeys(country);
        log("Выбор региона (Калифорния, США)");
        driver.findElement(By.xpath("//select[@name='zone_code']")).click();
        driver.findElement(By.xpath("//select[@name='zone_code']//option[@value='CA']")).click();
        log("Заполнение email");
        driver.findElement(By.name("email")).sendKeys(login);
        log("Заполнение номера телефона с кодом Калифорнии");
        driver.findElement(By.name("phone")).sendKeys(phoneRegionCode + getNumber());
        log("Первый ввод пароля (создание)");
        driver.findElement(By.name("password")).sendKeys(password);
        log("Подтверждение пароля");
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        log("Нажатие кнопки регистрации");
        driver.findElement(By.name("create_account")).click();
        log("Выход из учетной записи");
        driver.findElement(By.xpath(logOutXPath)).click();
        log("Вход в созданную учетную запись: введение логина (email)");
        driver.findElement(By.name("email")).sendKeys(login);
        log("Вход в созданную учетную запись: введение пароля");
        driver.findElement(By.name("password")).sendKeys(password);
        log("Нажатие кнопки входа в аккаунт");
        driver.findElement(By.name("login")).click();
        log("Выход из учетной записи");
        driver.findElement(By.xpath(logOutXPath)).click();
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

    private String getStringOfLetters() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
    }

    private String getNumber() {
        return String.valueOf(10000 + (int) (Math.random() * 89999));
    }

    private void log(String logInfo) {
        System.out.println(logInfo);
    }
}
