//package pages;
//
//import org.openqa.selenium.Cookie;
//import tests.TestBase;
//
//import static com.codeborne.selenide.Selenide.open;
//import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
//import static io.qameta.allure.Allure.step;
//import static java.lang.String.format;
//
//public class TestCasePage extends TestBase {
//    public TestCasePage testID() {
//        step("Получение id тест кейса", () -> {
//            open("/favicon.ico");
//            Cookie authCookie = new Cookie("ALLURE_TESTOPS_SESSION", session);
//            getWebDriver().manage().addCookie(authCookie);
//
//            String testCaseID = createTestCaseResponse.getId();
//            String testCaseURl = format("/project/3488/test-cases/%s", testCaseID);
//            open(testCaseURl);
//        });
//        return this;
//    }
//}
