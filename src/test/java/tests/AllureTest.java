package tests;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;


public class AllureTest extends TestBase {

    @Test
    public void testPrecondition() {
        String testCaseID = testCase.getId();
        testCase.setPrecondition("1234");
        testCase.setId(testCaseID);
        given()
                .log().all()
                .header("X-XSRF-TOKEN", token)
                .cookies("XSRF-TOKEN", token,
                        "ALLURE_TESTOPS_SESSION", session)
                .contentType("application/json;charset=UTF-8")
                .body(testCase)
                .when()
                .patch(format("/api/rs/testcase/%s", testCaseID))
                .then()
                .log().body();
    }


    @Test
    public void testSteps() {
        String testCaseID = testCase.getId();
        testCase.setName("1234");
        given()
                .log().all()
                .header("X-XSRF-TOKEN", token)
                .cookies("XSRF-TOKEN", token,
                        "ALLURE_TESTOPS_SESSION", session)
                .contentType("application/json;charset=UTF-8")
                .body(testCase)
                .when()
                .post(format("/api/rs/testcase/%s/scenario", testCaseID))
                .then()
                .log().body();
    }

    @Test
    public void testExpectedResult() {
        String testCaseID = testCase.getId();
        testCase.setExpectedResult("1234");
        given()
                .log().all()
                .header("X-XSRF-TOKEN", token)
                .cookies("XSRF-TOKEN", token,
                        "ALLURE_TESTOPS_SESSION", session)
                .contentType("application/json;charset=UTF-8")
                .body(testCase)
                .when()
                .patch(format("/api/rs/testcase/%s", testCaseID))
                .then()
                .log().body();
    }
}

