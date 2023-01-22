import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class ConstructorTest {
    private final String browser;
    public ConstructorTest(String browser) {
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Object[] getCredentials() {
        return new Object[][] {
                { "opera"},
                { "chrome"},
                        };
    }
    Main Main = page(Main.class); // создаётся экземпляр класса страницы Main
    @Before  public void Open() throws InterruptedException
    // открываем страницу
    {
        Configuration.browser=browser;
        startMaximized = true;// открывает окно на весь экран
        open("https://stellarburgers.nomoreparties.site");

    }
    @Test
    public void clickFillingsConstructor() throws InterruptedException {
        Main.clickFilling();
        Thread.sleep(1000);
        Main.matcherSelectedTab("Начинки");
    }
    @Test
    public void clickSauceConstructor() throws InterruptedException {
        Main.clickSauce();
        Main.matcherSelectedTab("Соусы");
    }
    @Test
    public void clickBunConstructor() throws InterruptedException {
        Main.clickFilling();
        Main.clickBun();
        Thread.sleep(1000);
        Main.matcherSelectedTab("Булки");
    }
    @After
    public void closeBrowser() {

        switchTo().window(getWebDriver().getWindowHandle()).close();}

}
