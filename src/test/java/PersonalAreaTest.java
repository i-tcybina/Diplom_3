import com.codeborne.selenide.Configuration;
import org.hamcrest.MatcherAssert;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PersonalAreaTest {
    private final String browser;
    public PersonalAreaTest(String browser) {
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Object[] getCredentials() {
        return new Object[][] {
                { "opera"},
                { "chrome"},
        };
    }

    PersonalArea PersonalArea = page(PersonalArea.class); // создаётся экземпляр класса страницы Autorization
    Authorization Authorization = page(Authorization.class); // создаётся экземпляр класса страницы Autorization
    Registration Registration = page(Registration.class); // создаётся экземпляр класса страницы Autorization
    Main Main = page(Main.class); // создаётся экземпляр класса страницы Main

    @Before  public void Open() throws InterruptedException
    // открываем страницу
    {
        Configuration.browser=browser;
        startMaximized = true;// открывает окно на весь экран
        open("https://stellarburgers.nomoreparties.site");
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        Authorization.clickRegister();
        Registration.setName("1Ira");
        Registration.setEmail("1itatest@mail.com");
        Registration.setPassword("1Ira123");
        Registration.clickRegistrationButton();
        Thread.sleep(1000);
        Authorization.setEmail("1itatest@mail.com");
        Authorization.setPassword("1Ira123");
        Authorization.clickLoginButton();
            }

    @Test
    public void enterPersonalArea() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        PersonalArea.matcherAssertName("1Ira");
    }

    @Test
    public void enterMainFromPersonalArea() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        Main.clickСonstructor();
        Main.matcherTextCollectBurger("Соберите бургер");
    }

    @Test
    public void clickLogoFromPersonalArea() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        Main.clickLogo();
        Main.matcherTextCollectBurger("Соберите бургер");
    }
    @Test
    public void exit() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        PersonalArea.clickButtonExit();
        MatcherAssert.assertThat(Authorization.getInput().getText(), containsString("Вход"));
    }

    @After
    public void closeBrowser() {

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        String token = given()
                .header("Content-type", "application/json")
                .body("{\"email\": \"1itatest@mail.com\", \"password\": \"1Ira123\"}")
                .when()
                .post("/api/auth/login")
                .then().extract().body().path("accessToken");
        if (token != null)
        {
            given()
                    .header("Content-type", "application/json")
                    .auth().oauth2(token.replaceFirst("Bearer ", ""))
                    .body("{\"authorization\": \"token\"}")
                    .when()
                    .delete("/api/auth/user")
                    .then().statusCode(202);}
        switchTo().window(getWebDriver().getWindowHandle()).close();
    }
}
