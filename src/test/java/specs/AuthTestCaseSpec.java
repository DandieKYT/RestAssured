package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.TestBase;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;


public class AuthTestCaseSpec extends TestBase {
    static String token = "38dd8d06-c8a1-45ea-af9a-7eba2dd09077", session = "b68e6bbe-e67c-4828-85b4-3f828faf08bd";
    public static RequestSpecification authRequestSpec = (RequestSpecification) with()
            .baseUri("https://allure.autotests.cloud")
            .filter(withCustomTemplates())
            .log().uri()
            .log().all()
            .header("X-XSRF-TOKEN", token)
            .cookies("XSRF-TOKEN", token,
                    "ALLURE_TESTOPS_SESSION", session)
            .contentType("application/json;charset=UTF-8")
            .post("/api/rs/testcasetree/leaf?projectId=3488&treeId=&");
    public static ResponseSpecification authResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();

}