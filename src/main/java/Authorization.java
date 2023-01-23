import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Authorization {

    @FindBy(how = How.XPATH, using = "//*[@name=\"name\"]")
    private SelenideElement email;
    public SelenideElement getEmail() {
        return email;
    }
    void setEmail(String email) {
        getEmail().click();
        getEmail().sendKeys(email);
    }
    @FindBy(how = How.XPATH, using = "//*[@name=\"Пароль\"]")
    private SelenideElement password;
    public SelenideElement getPassword() {
        return password;
    }
    void setPassword(String password) {
        getPassword().click();
        getPassword().sendKeys(password);
    }

    @FindBy(how = How.XPATH, using = "//*[@class=\"button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa\"]")
    private SelenideElement loginButton;

    //Нажать кнопку вход
    void clickLoginButton() {
        loginButton.click();
    }

    //ссылка "регистрация"
    @FindBy(how = How.XPATH, using = "//*[@href=\"/register\"]")
    private SelenideElement register;

    //Нажать ссылку "Регистарция"
     void clickRegister() {
        register.click();
    }

    //ссылка "восстановить пароль"
    @FindBy(how = How.XPATH, using = "//*[@href=\"/forgot-password\"]")
    private SelenideElement linkForgotPassword;

    //Нажать ссылку "восстановить пароль"
    void clickLinkForgotPassword() {
        linkForgotPassword.click();
    }

    //Вход
    @FindBy(how = How.XPATH, using = "//*[@class=\"Auth_login__3hAey\"]")
    private SelenideElement input;

    //
    public SelenideElement getInput() {
        return input;
    }

}
