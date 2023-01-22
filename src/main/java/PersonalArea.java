import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static org.hamcrest.CoreMatchers.containsString;


public class PersonalArea {
    @FindBy(how = How.XPATH, using = "//*[@class=\"text input__textfield text_type_main-default input__textfield-disabled\"]")
    private ElementsCollection registrationFields;
    public ElementsCollection getRegistrationFields() {
        return registrationFields;
    }
    //
    void matcherAssertName(String name) {
        MatcherAssert.assertThat(getRegistrationFields().get(0).getValue(), containsString(name));
            }

    @FindBy(how = How.XPATH, using = "//*/li[3]/button")
    private SelenideElement buttonExit;
    void clickButtonExit()  {
        buttonExit.click();
    }

}
