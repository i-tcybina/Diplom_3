import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Parameterized.class)
public class EnterTest {
    private final String browser;
    public EnterTest(String browser) {
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
        Authorization.getInput().shouldHave(exactText("Вход\n" +
                "Email\n" +
                "Пароль\n" +
                "Войти\n" +
                "Вы — новый пользователь? Зарегистрироваться\n" +
                "Забыли пароль? Восстановить пароль"), Duration.ofSeconds(5));
        //Thread.sleep(1000);
        Main.clickСonstructor();
    }

    @Test
    public void enterWithMainButton() throws InterruptedException {
        Main.clickRegistrationButton();

    }
    @Test
    public void enterWithPersonalArea() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
                   }

    @Test
    public void enterWithRegistrationForm() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        Authorization.clickRegister();
        Registration.clickLinkEnter();
    }

    @Test
    public void enterWithForgotPasswordForm() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        Authorization.clickLinkForgotPassword();
        Registration.clickLinkEnter();
    }

    @After
    public void closeBrowser() {
        Authorization.setEmail("1itatest@mail.com");
        Authorization.setPassword("1Ira123");
        Authorization.clickLoginButton();
        Main.clickPersonalArea();
        PersonalArea.matcherAssertName("1Ira");
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
