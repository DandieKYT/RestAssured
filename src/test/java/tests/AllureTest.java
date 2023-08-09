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
import static specs.AuthSpec.authRequestSpec;


public class AllureTest extends TestBase {
    String projectId = "3488";

    @Test
    public void testCase() {
        Faker faker = new Faker();
        String someName = faker.name().fullName();
        step("Авторизация");

        testCase.setName(someName);
        CreateTestCaseResponse createTestCaseResponse = step("Create testcase", () ->
                given(authRequestSpec)
                        .body(testCase)
                        .when()
                        .post()
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
            testCase.setPrecondition(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077")
                    .cookies("XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077",
                            "ALLURE_TESTOPS_SESSION", "5cb11656-c10d-4ee4-b142-5b201dee9f90")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCase)
                    .when()
                    .patch(format("/api/rs/testcase/%s", testCaseID))
                    .then()
                    .log().body();
        });
        step("Edit testcase steps", () -> {
            String testCaseID = createTestCaseResponse.getId();
            testCase.setName(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077")
                    .cookies("XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077",
                            "ALLURE_TESTOPS_SESSION", "5cb11656-c10d-4ee4-b142-5b201dee9f90")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCase)
                    .when()
                    .post(format("/api/rs/testcase/%s/scenario", testCaseID))
                    .then()
                    .log().body();
        });
        step("Edit testcase expectedResult", () -> {
            String testCaseID = createTestCaseResponse.getId();
            testCase.setExpectedResult(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077")
                    .cookies("XSRF-TOKEN", "38dd8d06-c8a1-45ea-af9a-7eba2dd09077",
                            "ALLURE_TESTOPS_SESSION", "5cb11656-c10d-4ee4-b142-5b201dee9f90")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCase)
                    .when()
                    .patch(format("/api/rs/testcase/%s", testCaseID))
                    .then()
                    .log().body();

        });


    }

}
