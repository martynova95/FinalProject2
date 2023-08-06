package UiTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends AbstractPage {

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@type='text']")
    private WebElement input_username;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement input_password;
    @FindBy(xpath = "//button[@form='login']")
    private WebElement button_login;
    @FindBy(xpath = "//div[@class='error-block svelte-uwkxn9']/h2")
    private WebElement error_code;
    @FindBy(xpath = "//div[@class='error-block svelte-uwkxn9']/p[1]")
    private WebElement error_message;
    @FindBy(xpath = "//li[contains(@class, 'surface')]")
    private WebElement button_hello;

    public AuthPage loginValid() {
        input_username.sendKeys("Antil");
        input_password.sendKeys("bf1af9fa93");
        button_login.click();
        return this;
    }
    public AuthPage loginMinChar() {
        input_username.sendKeys("qqq");
        input_password.sendKeys("b2ca678b4c");
        button_login.click();
        return this;
    }
    public AuthPage loginMaxChar() {
        input_username.sendKeys("01234567890123456789");
        input_password.sendKeys("be497c2168");
        button_login.click();
        return this;
    }
    public AuthPage loginRus() {
        input_username.sendKeys("пароль");
        input_password.sendKeys("e242f36f4f");
        button_login.click();
        return this;
    }
    public AuthPage loginSpecChar() {
        input_username.sendKeys("!@#$");
        input_password.sendKeys("3a4d92a120");
        button_login.click();
        return this;
    }
    public AuthPage loginLessThanMinChar() {
        input_username.sendKeys("ww");
        input_password.sendKeys("ad57484016");
        button_login.click();
        return this;
    }
    public AuthPage loginMoreThanMaxChar() {
        input_username.sendKeys("asasasasasasasasasasa");
        input_password.sendKeys("480243012b");
        button_login.click();
        return this;
    }
    public AuthPage loginUnregistered() {
        input_username.sendKeys("Unregistered");
        input_password.sendKeys("be497c2168");
        button_login.click();
        return this;
    }
    public AuthPage loginIncorrectPassword() {
        input_username.sendKeys("Antil");
        input_password.sendKeys("пароль");
        button_login.click();
        return this;
    }
    public AuthPage loginWithoutLoginAndPassword() {
        input_username.sendKeys("");
        input_password.sendKeys("");
        button_login.click();
        return this;
    }
    public String getLogin() {
        String login = button_hello.getText().substring(7);
        return login;
    }
    public WebElement getError_code() {
        return error_code;
    }
    public WebElement getError_message() {
        return error_message;
    }
}
