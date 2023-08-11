package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import models.CreateTestCaseResponse;
import models.LombokModel;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.AuthSpec.authRequestSpec;
import static specs.AuthSpec.authResponseSpec;



public class TestBase {
    String token = "38dd8d06-c8a1-45ea-af9a-7eba2dd09077", session = "b68e6bbe-e67c-4828-85b4-3f828faf08bd";
    CreateTestCaseResponse testCase = new CreateTestCaseResponse();
    @BeforeAll

         public static void setUp() {
        CreateTestCaseResponse testCase = new CreateTestCaseResponse();
        Configuration.baseUrl = "https://allure.autotests.cloud";
        Configuration.holdBrowserOpen = true;
        String session = "b68e6bbe-e67c-4828-85b4-3f828faf08bd";

        testCase.setName("1234");
        CreateTestCaseResponse createTestCaseResponse = step("Create testcase", () -> {
            given(authRequestSpec)
                    .then()
                    .log().status()
                    .log().body()
                    .then()
                    .spec(authResponseSpec)
                    .extract()as(CreateTestCaseResponse.class);
        });



        open("/favicon.ico");
        Cookie authCookie = new Cookie("ALLURE_TESTOPS_SESSION", session);
        getWebDriver().manage().addCookie(authCookie);

        String testCaseID = createTestCaseResponse.getId();
        String testCaseURl = format("/project/3488/test-cases/%s", testCaseID);
        open(testCaseURl);
    }}