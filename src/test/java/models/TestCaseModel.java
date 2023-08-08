package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class TestCaseModel {
    String precondition, name, expectedResult, id;
}
