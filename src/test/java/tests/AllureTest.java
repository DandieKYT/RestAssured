package tests;


import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class AllureTest extends TestBase {
    String projectId = "3488";

    @Test
    public void testCase(){
        Faker faker = new Faker();
        String someName = faker.name().fullName();

        step("Create testcase", () -> {

            testCase.setName(someName);

            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "8cb8e24b-41f8-4ed1-a440-ac4edf4afd84")
                    .cookies("XSRF-TOKEN", "8cb8e24b-41f8-4ed1-a440-ac4edf4afd84",
                            "ALLURE_TESTOPS_SESSION", "25595e6a-e1ce-4053-ae04-e1d889a3342e")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCase)
                    .when()
                    .post("https://allure.autotests.cloud/api/rs/testcasetree/leaf?projectId=3488&treeId=&")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .body("name", is(someName));


    });
        step("Edit testcase preconditions", () -> {

            testCaseModel.setPrecondition(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "8cb8e24b-41f8-4ed1-a440-ac4edf4afd84")
                    .cookies("XSRF-TOKEN", "8cb8e24b-41f8-4ed1-a440-ac4edf4afd84",
                            "ALLURE_TESTOPS_SESSION", "25595e6a-e1ce-4053-ae04-e1d889a3342e")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCaseModel)
                    .when()
                    .patch("https://allure.autotests.cloud/api/rs/testcase/25129 ");

        });
        step("Edit testcase steps", () -> {

            testCaseModel.setName(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "8cb8e24b-41f8-4ed1-a440-ac4edf4afd84")
                    .cookies("XSRF-TOKEN", "8cb8e24b-41f8-4ed1-a440-ac4edf4afd84",
                            "ALLURE_TESTOPS_SESSION", "25595e6a-e1ce-4053-ae04-e1d889a3342e")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCaseModel)
                    .when()
                    .post("https://allure.autotests.cloud/api/rs/testcase/25129 ");
        });
        step("Edit testcase expectedResult", () -> {

            testCaseModel.setExpectedResult(someName);
            given()
                    .log().all()
                    .header("X-XSRF-TOKEN", "8cb8e24b-41f8-4ed1-a440-ac4edf4afd84")
                    .cookies("XSRF-TOKEN", "8cb8e24b-41f8-4ed1-a440-ac4edf4afd84",
                            "ALLURE_TESTOPS_SESSION", "25595e6a-e1ce-4053-ae04-e1d889a3342e")
                    .contentType("application/json;charset=UTF-8")
                    .body(testCaseModel)
                    .when()
                    .patch("https://allure.autotests.cloud/api/rs/testcase/25129 ");
        });


}
}
