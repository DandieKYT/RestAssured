package tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static specs.AuthSpec.authRequestSpec;


public class AllureTest extends TestBase {

    @Test
    @DisplayName("Добавление предусловий")
    public void testPrecondition() {
        String testCaseID = testCase.getId();
        testCase.setPrecondition("1234");
        testCase.setId(testCaseID);
        given(authRequestSpec)
                .body(testCase)
                .when()
                .patch(format("/api/rs/testcase/%s", testCaseID))
                .then()
                .log().body();
    }


    @Test
    @DisplayName("Добавление шагов")
    public void testSteps() {
        String testCaseID = testCase.getId();
        testCase.setName("1234");
        given(authRequestSpec)
                .body(testCase)
                .when()
                .post(format("/api/rs/testcase/%s/scenario", testCaseID))
                .then()
                .log().body();
    }

    @Test
    @DisplayName("Добавление ожидаемого результата")
    public void testExpectedResult() {
        String testCaseID = testCase.getId();
        testCase.setExpectedResult("1234");
        given(authRequestSpec)
                .body(testCase)
                .when()
                .patch(format("/api/rs/testcase/%s", testCaseID))
                .then()
                .log().body();
    }
}

