package tests;

import com.codeborne.selenide.Configuration;
import models.CreateTestCaseResponse;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;
import pages.BasePage;
import pages.TestCaseResultPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.AuthTestCaseSpec.authRequestSpec;
import static specs.AuthTestCaseSpec.authResponseSpec;



public class TestBase {
    TestCaseResultPage testCaseResultPage = new TestCaseResultPage();
    BasePage basePage = new BasePage();
    String token = "84434f35-0215-4240-9217-81358f1b68f0", session = "4bc227f6-9943-474c-a1b7-d15a8f67e18d";
    CreateTestCaseResponse testCase = new CreateTestCaseResponse();
    @BeforeAll

         static void setUp() {
        CreateTestCaseResponse testCase = new CreateTestCaseResponse();
        Configuration.baseUrl = "https://allure.autotests.cloud";
        Configuration.holdBrowserOpen = true;
        String session = "b68e6bbe-e67c-4828-85b4-3f828faf08bd";

        testCase.setName("SomeCase");
        CreateTestCaseResponse createTestCaseResponse =
            given(authRequestSpec)
                    .body(authRequestSpec)
                    .post()
                    .then()
                    .spec(authResponseSpec)
                    .extract().as(CreateTestCaseResponse.class);




        open("/favicon.ico");
        Cookie authCookie = new Cookie("ALLURE_TESTOPS_SESSION", session);
        getWebDriver().manage().addCookie(authCookie);

        String testCaseID = createTestCaseResponse.getId();
        String testCaseURl = format("/project/3488/test-cases/%s", testCaseID);
        open(testCaseURl);
    }}