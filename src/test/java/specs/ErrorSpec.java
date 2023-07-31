package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class ErrorSpec {
    public static RequestSpecification errorRequestSpec = with()
            .baseUri("https://reqres.in/api/users/23")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON);



    public static ResponseSpecification errorResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .build();
}

