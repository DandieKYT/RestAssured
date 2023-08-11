package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTestCaseResponse {
    String name;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPrecondition() {
        return precondition;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    String id;
    String precondition;
    String expectedResult;
}
