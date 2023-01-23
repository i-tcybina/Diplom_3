import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

class Registration {

    //поля ввода Имя, Email, Пароль
    @FindBy(how = How.XPATH, using = "//*[@class=\"text input__textfield text_type_main-default\"]")
    private ElementsCollection registrationFields;
    public ElementsCollection getRegistrationFields() {
        return registrationFields;
    }
    //ввод почты
    void setName(String name) {
        getRegistrationFields().get(0).click();
        getRegistrationFields().get(0).sendKeys(name);
    }
    //ввод пароля
    void setEmail(String email) {
        getRegistrationFields().get(1).click();
        getRegistrationFields().get(1).sendKeys(email);
    }
    //ввод пароля
    void setPassword(String password) {
        getRegistrationFields().get(2).click();
        getRegistrationFields().get(2).sendKeys(password);
    }
//кнопка зарегистрироваться
    @FindBy(how = How.XPATH, using = "//*[@class=\"button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa\"]")
    private SelenideElement registrationButton;

    //
    void clickRegistrationButton()  {
        registrationButton.click();
            }
    @FindBy(how = How.XPATH, using = "//*[@class=\"input__error text_type_main-default\"]")
    private SelenideElement error;

    //
    public SelenideElement getError() {
        return error;
    }


    @FindBy(how = How.XPATH, using = "//*[@href=\"/login\"]")
    private SelenideElement linkEnter;

    //
    void clickLinkEnter()  {
        linkEnter.click();
    }
}













