
import com.swapi.BaseService;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class RestApi {


    @Test
    public void getUrl() {
        String basePath = "/people/1/";

        BaseService.get(basePath)
                //.prettyPrint()
                .then()
                .statusCode(200)
                .body("name", equalTo("Luke Skywalker"))
                .log().ifValidationFails(LogDetail.BODY).statusCode(200);
    }


}
