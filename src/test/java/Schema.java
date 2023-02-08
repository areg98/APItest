import org.testng.annotations.Test;

import static com.swapi.constant.JSchemaConst.MIN_JSON_PATH;
import static com.swapi.constant.ReadFile.readLineByLineJava;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Schema {

    @Test
    public void validates_min_schema_in_classpath() {
        String expectedJson = readLineByLineJava(MIN_JSON_PATH);
        assertThat(expectedJson, matchesJsonSchemaInClasspath("Schema/min.schema.json"));
    }
}
