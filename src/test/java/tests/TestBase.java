package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import models.CreateTestCaseResponse;
import models.TestCaseModel;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    CreateTestCaseResponse testCase = new CreateTestCaseResponse();
    TestCaseModel testCaseModel = new TestCaseModel();
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://allure.autotests.cloud";
        Configuration.holdBrowserOpen = true;

        RestAssured.baseURI = "https://allure.autotests.cloud";
    }
}
