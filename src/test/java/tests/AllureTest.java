package tests;


import models.CreateTestCaseResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;


public class AllureTest extends TestBase {

    @Test
    public void testPrecondition() {
        String testCaseID = createTestCaseResponse.getId();
        testCase.setPrecondition("1234");
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
    }


    @Test
    public void testSteps() {
        String testCaseID = createTestCaseResponse.getId();
        testCase.setName("1234");
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
    }

    @Test
    public void testExpectedResult() {
        String testCaseID = createTestCaseResponse.getId();
        testCase.setExpectedResult("1234");
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
    }
}

