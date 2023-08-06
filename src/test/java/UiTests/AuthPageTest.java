package UiTests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Story("Authorization on the GeekTest website Tests")
public class AuthPageTest extends AbstractTest {

    private void saveScreen(String name) throws IOException {
        File file = MyUtils.makeScreenshot(getDriver(),name + " " + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Test
    @DisplayName("Authorization with valid data")
    @Description("Valid - username and password")
    public void authValidTest() throws InterruptedException, IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginValid();
        Thread.sleep(1000);
        saveScreen("Авторизация с валидными значениями юзернейм и пароль");
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
        assertEquals("Antil", authPage.getLogin());
    }

    @Test
    @DisplayName("Authorization with valid data")
    @Description("Valid - username (minimum number of characters) and valid - password")
    public void authMinCharTest() throws InterruptedException, IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMinChar();
        Thread.sleep(1000);
        saveScreen("Авторизация с валидными значениями юзернейм (минимальное кол-во символов) и пароль");
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
        assertEquals("qqq", authPage.getLogin());
    }

    @Test
    @DisplayName("Authorization with valid data")
    @Description("Valid - username (maximum number of characters) and valid - password")
    public void authMaxCharTest() throws InterruptedException, IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMaxChar();
        Thread.sleep(1000);
        saveScreen("Авторизация с валидными значениями юзернейм (максимальное кол-во символов) и пароль");
        assertEquals("https://test-stand.gb.ru/", getDriver().getCurrentUrl());
        assertEquals("01234567890123456789", authPage.getLogin());
    }
    @Test
    @DisplayName("Authorization with invalid data")
    @Description("Unregistered user")
    public void authUnregisteredTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginUnregistered();
        saveScreen("Авторизация незарегистрированного пользователя");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Authorization with invalid data")
    @Description("Invalid - username (russian letters (not Latin)) and valid - password")
    public void authRusTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginRus();
        saveScreen("Авторизация с невалидным значением юзернейм (русские буквы (не латиница)) и валидный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Authorization with invalid data")
    @Description("Invalid - username (special characters) and valid - password")
    public void authSpecCharTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginSpecChar();
        saveScreen("Авторизация с невалидным значением юзернейм (специальные символы) и валидный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Authorization with invalid data")
    @Description("Invalid - username (less than the minimum number of character values (at least 3)) and valid - password")
    public void authLessThanMinCharTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginLessThanMinChar();
        saveScreen("Авторизация с невалидным значением юзернейм (2 символьных значения (не менее 3)) и валидный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Authorization with invalid data")
    @Description("Invalid - username (more than the maximum number of character values (no more than 20)) and valid - password")
    public void authMoreThanMaxCharTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginMoreThanMaxChar();
        saveScreen("Авторизация с невалидным значением юзернейм (21 символьное значение (не более 20)) и валидный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }

    @Test
    @DisplayName("Authorization with invalid data")
    @Description("Valid - username and incorrect password")
    public void authIncorrectPasswordTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginIncorrectPassword();
        saveScreen("Авторизация с валидным значением юзернейм и неправильный пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }


    @Test
    @DisplayName("Authorization with invalid data")
    @Description("Empty values username and password")
    public void authorizationWithoutLoginAndPasswordTest() throws IOException {
        AuthPage authPage = new AuthPage(getDriver());
        authPage.loginWithoutLoginAndPassword();
        saveScreen("Авторизация с пустыми значениями юзернейм и пароль");
        assertEquals("Invalid credentials.", authPage.getError_message().getText());
        assertEquals("401", authPage.getError_code().getText());
    }
}
