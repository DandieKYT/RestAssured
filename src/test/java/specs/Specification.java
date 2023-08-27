package specs;

import authentication.Authentication;
import config.AuthConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class Specification {
    private static final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);
    private static final String BASE_URL = "https://allure.autotests.cloud";
    private static final String BASE_PATCH = "/api/rs";
    public static RequestSpecification requestSpec =
            with()
                    .header("Authorization", "Api-Token " + authConfig.token())
                    .cookie("ALLURE_TESTOPS_SESSION" , Authentication.getInstance().authenticate())
                    .baseUri(BASE_URL)
                    .basePath(BASE_PATCH)
                    .log().all()
                    .contentType(JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .build();

}
