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

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class RegistrationTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String login = getStringOfLetters() + "@gmail.com";
    private final String password = getStringOfLetters();
    private final String country = "United States\n";
    private final String beginOfLoginXPath = "//*[@id=\"box-account-login\"]/div/form/table/tbody/";
    private final String beginOfRegistrationXPath = "//*[@id=\"create-account\"]/div/form/table/tbody/";
    private final String logOutXPath = "//*[@id=\"box-account\"]/div/ul/li[4]/a";
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
        driver.findElement(By.xpath(beginOfLoginXPath + "tr[5]/td/a"))
                .click();
        //wait.until(titleIs("Create Account | My Store"));
        log("Заполнение имени пользователя");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[2]/td[1]/input"))
                .sendKeys(getStringOfLetters());
        log("Заполнение фамилии пользователя");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[2]/td[2]/input"))
                .sendKeys(getStringOfLetters());
        log("Заполнение адреса (адрес 1)");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[3]/td[1]/input"))
                .sendKeys(getStringOfLetters() + getNumber());
        log("Заполнение почтового кода (США)");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[4]/td[1]/input")).sendKeys(getNumber());
        log("Заполнение поля города");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[4]/td[2]/input"))
                .sendKeys(getStringOfLetters());
        log("Выбор страны (United States)");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[5]/td[1]/span[2]/span[1]")).click();
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(country);
        log("Выбор региона (Калифорния, США)");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[5]/td[2]/select")).click();
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[5]/td[2]/select/option[12]")).click();
        log("Заполнение email'а");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[6]/td[1]/input")).sendKeys(login);
        log("Заполнение номера телефона с кодом Калифорнии");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[6]/td[2]/input"))
                .sendKeys(phoneRegionCode + getNumber());
        log("Первый ввод пароля (создание)");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[8]/td[1]/input")).sendKeys(password);
        log("Подтверждение пароля");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[8]/td[2]/input")).sendKeys(password);
        log("Нажатие кнопки регистрации");
        driver.findElement(By.xpath(beginOfRegistrationXPath + "tr[9]/td/button")).click();
        log("Выход из учетной записи");
        driver.findElement(By.xpath(logOutXPath)).click();
        log("Вход в созданную учетную запись: введение логина (email)");
        driver.findElement(By.xpath(beginOfLoginXPath + "tr[1]/td/input")).sendKeys(login);
        log("Вход в созданную учетную запись: введение пароля");
        driver.findElement(By.xpath(beginOfLoginXPath + "tr[2]/td/input")).sendKeys(password);
        log("Нажатие кнопки входа в аккаунт");
        driver.findElement(By.xpath(
                beginOfLoginXPath + "tr[4]/td/span/button[1]")).click();
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

    private void log(String logInfo){
        System.out.println(logInfo);
    }
}
