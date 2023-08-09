package specs;

import io.restassured.specification.RequestSpecification;
import tests.TestBase;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;


public class AuthSpec extends TestBase {
    static String xsfr = "38dd8d06-c8a1-45ea-af9a-7eba2dd09077";
    static String session = "5cb11656-c10d-4ee4-b142-5b201dee9f90";
    public static RequestSpecification authRequestSpec = (RequestSpecification) with()
            .baseUri("https://allure.autotests.cloud")
            .filter(withCustomTemplates())
            .log().uri()
            .header("X-XSRF-TOKEN", "" + xsfr)
            .cookies("XSRF-TOKEN", "" + xsfr,
                    "ALLURE_TESTOPS_SESSION", "" + session)
            .contentType("application/json;charset=UTF-8")
            .post("/api/rs/testcasetree/leaf?projectId=3488&treeId=&");
}
