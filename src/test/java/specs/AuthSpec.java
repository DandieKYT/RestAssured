package specs;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class AuthSpec {
    static String token = "38dd8d06-c8a1-45ea-af9a-7eba2dd09077", session = "b68e6bbe-e67c-4828-85b4-3f828faf08bd";
    public static RequestSpecification authRequestSpec = (RequestSpecification) with()
            .baseUri("https://allure.autotests.cloud")
            .filter(withCustomTemplates())
            .log().uri()
            .log().all()
            .header("X-XSRF-TOKEN", token)
            .cookies("XSRF-TOKEN", token,
                    "ALLURE_TESTOPS_SESSION", session)
            .contentType("application/json;charset=UTF-8");
}
