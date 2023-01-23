import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static org.hamcrest.CoreMatchers.containsString;


public class Main {
    //кнопки "Конструктор", "Лента заказов", "Личный кабинет"
    @FindBy(how = How.XPATH, using = "//*[@class=\"AppHeader_header__linkText__3q_va ml-2\"]")
    private ElementsCollection headerButton;
    public ElementsCollection getHeaderButton() {
        return headerButton;
    }
    //Нажать "Личный кабинет"
    void clickPersonalArea(){
        getHeaderButton().get(2).click();
    }
    void clickСonstructor(){
        getHeaderButton().get(0).click();
    }
    @FindBy(how = How.XPATH, using = "//*[@class=\"button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg\"]")
    private SelenideElement registrationButton;

    void clickRegistrationButton()  {
    registrationButton.click();
    }
    @FindBy(how = How.XPATH, using = "//*[@class=\"text text_type_main-large mb-5 mt-10\"]")
    private SelenideElement textCollectBurger;

    void matcherTextCollectBurger(String name) {
        MatcherAssert.assertThat(textCollectBurger.getText(), containsString(name));
    }

    @FindBy(how = How.XPATH, using = "//*[@class=\"AppHeader_header__logo__2D0X2\"]")
    private SelenideElement mainLogо;
    void clickLogo()  {
        mainLogо.click();
    }


    //вкладки конструктора
    @FindBy(how = How.XPATH, using = "//*[@class=\"text text_type_main-default\"]")
    private ElementsCollection constructorTab;
    public ElementsCollection getConstructorTab() {
        return constructorTab;
    }

    void clickBun(){
        getConstructorTab().get(0).click();
    }
    void clickSauce(){
        getConstructorTab().get(1).click();
    }
    void clickFilling(){
        getConstructorTab().get(2).click();
    }

    @FindBy(how = How.XPATH, using = "//*[@class=\"tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect\"]")
    private SelenideElement selectedTab;
    public SelenideElement getSelectedTab() {
        return selectedTab;
    }
    //
    void matcherSelectedTab(String name) {
        MatcherAssert.assertThat(getSelectedTab().getText(), containsString(name));
    }


}

















