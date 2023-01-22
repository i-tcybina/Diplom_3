import org.hamcrest.MatcherAssert;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import io.restassured.RestAssured;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.io.IOException;

@RunWith(Parameterized.class)
public class RegistrationTest {
     private String browser;
     public RegistrationTest(String browser) {
     this.browser = browser;
     }
     @Parameterized.Parameters
     public static Object[] getCredentials() {
    return new Object[][] {
    { "yandexdriver"},
    { "chromedriver"},
     };
    }
    private static ChromeDriverService serviceYandex;
    private static ChromeDriverService serviceChrome;
    private WebDriver driver;

    @BeforeClass

    public static void createAndStartService() throws IOException {
        serviceYandex = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/Diplom/yandexdriver.exe"))
                .usingAnyFreePort()
                .build();
        serviceYandex.start();
        serviceChrome = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/Diplom/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        serviceChrome.start();
    }
    Authorization Authorization = page(Authorization.class); // создаётся экземпляр класса страницы Autorization
    Registration Registration = page(Registration.class); // создаётся экземпляр класса страницы Autorization
    Main Main = page(Main.class); // создаётся экземпляр класса страницы Main

   @Before  public void Open()
     // открываем страницу
    {
        if (browser=="yandexdriver"){
                driver = new RemoteWebDriver(serviceYandex.getUrl(), new ChromeOptions());}
        if (browser=="chromedriver") {driver = new RemoteWebDriver(serviceChrome.getUrl(), new ChromeOptions());}
        setWebDriver(driver);
        //Configuration.browser=browser;
        startMaximized = true;// открывает окно на весь экран
        open("https://stellarburgers.nomoreparties.site");
    }

    @Test
    public void errorRegistration() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        Authorization.clickRegister();
        Registration.setName("Ira");
        Registration.setEmail("itatest@mail.com");
        Registration.setPassword("Ira");
        Registration.clickRegistrationButton();
        MatcherAssert.assertThat(Registration.getError().getText(), containsString("Некорректный пароль"));
    }
    @Test
    public void registration() throws InterruptedException {
        Main.clickPersonalArea(); //Нажать "Личный кабинет"
        Authorization.clickRegister();
        Registration.setName("1Ira");
        Registration.setEmail("1itatest@mail.com");
        Registration.setPassword("1Ira123");
        Registration.clickRegistrationButton();
        Thread.sleep(1000);
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
    @AfterClass
    public static void stopService() {
        serviceYandex.stop();
        serviceChrome.stop();
    }
}

