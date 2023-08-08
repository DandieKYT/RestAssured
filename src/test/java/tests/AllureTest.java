package tests;


import com.github.javafaker.Faker;

import models.CreateTestCaseResponse;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;


public class AllureTest extends TestBase {
    String projectId = "3488";

    @Test
    public void testCase() {
        Faker faker = new Faker();
        String someName = faker.name().fullName();
        step("Авторизация");

        testCase.setName(someName);
        CreateTestCaseResponse createTestCaseResponse = step("Create testcase", () ->
                given()
                        .log().all()
                        .header("X-XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077")
                        .cookies("XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077",
                                "ALLURE_TESTOPS_SESSION", "5cb11656-c10d-4ee4-b142-5b201dee9f90")
                        .contentType("application/json;charset=UTF-8")
                        .body(testCase)
                        .queryParam("project Id", projectId)
                        .when()
                        .post("https://allure.autotests.cloud/api/rs/testcasetree/leaf?projectId=3488&treeId=&")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(CreateTestCaseResponse.class));


        step("PickUp id Test case", () -> {
            open("/favicon.ico");
            Cookie authCookie = new Cookie("ALLURE_TESTOPS_SESSION", "5cb11656-c10d-4ee4-b142-5b201dee9f90");
            getWebDriver().manage().addCookie(authCookie);

            String testCaseID = createTestCaseResponse.getId();
            String testCaseURl = format("/project/%s/test-cases/%s", projectId, testCaseID);
            open(testCaseURl);

        });
        step("Edit testcase preconditions", () -> {
            String testCaseID = createTestCaseResponse.getId();
            testCaseModel.setPrecondition(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077")
                    .cookies("XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077",
                            "ALLURE_TESTOPS_SESSION", "5cb11656-c10d-4ee4-b142-5b201dee9f90")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCaseModel)
                    .when()
                    .patch(format("/api/rs/testcase/%s", testCaseID));

        });
        step("Edit testcase steps", () -> {

            testCaseModel.setName(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077")
                    .cookies("XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077",
                            "ALLURE_TESTOPS_SESSION", "5cb11656-c10d-4ee4-b142-5b201dee9f90")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCaseModel)
                    .when()
                    .post("https://allure.autotests.cloud/api/rs/testcase/%s");
        });
        step("Edit testcase expectedResult", () -> {

            testCaseModel.setExpectedResult(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077")
                    .cookies("XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077",
                            "ALLURE_TESTOPS_SESSION", "5cb11656-c10d-4ee4-b142-5b201dee9f90")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCaseModel)
                    .when()
                    .patch("https://allure.autotests.cloud/api/rs/testcase/%s");
        });


    }
}
