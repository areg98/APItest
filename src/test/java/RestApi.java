import com.swapi.helper.PojoHelper;
import com.swapi.pojo.UserUpdateResponse;
import com.swapi.service.BaseService;
import com.swapi.service.UserService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.swapi.constant.Urls.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestApi {


//    @Test
//    public void getUrl() {
//        String basePath = String.format(USER_LIST_URL, 2);
//
////        BaseService.get(basePath)
//                //.prettyPrint()
//                .then()
//                .statusCode(200)
//                .statusCode(200)
//                .log().ifValidationFails(LogDetail.BODY).statusCode(200);
//    }

    @Test
    public void getUsers() {
        UserService.getUsers(2)
                .then()
                .body("data.avatar", everyItem(isA(String.class)),
                        "data.email", everyItem(endsWith("@reqres.in")));

    }


    @Test
    public void addUser() {
        UserService.addUser("babken", "leader")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", isA(String.class));

    }


    /** ============================================================================================*/
    /** ============================= Change all test and methods===================================*/
    /**
     * ============================================================================================
     */


//    @Test
//    public void updateUser() {
//        String requestBody = "{\n"
//                + "  \"name\": \"morpheus\",\n"
//                + "  \"job\": \"zion resident\"\n"
//                + "}";
//
//        Response response = BaseService.put(String.format(UPDATE_USER_URL, 2), requestBody);
//        response.then().log().all();
//
//        assertEquals(response.statusCode(), 200);
////        assertTrue(response.jsonPath().getString("name").contains("morpheus"));
////        assertTrue(response.jsonPath().getString("job").contains("zion resident"));
//
//        System.out.println("Response: " + response.asString());
//        System.out.println("Status Code: " + response.statusCode());
//
//    }
    @Test
    public void updateUser() {

        String requestBody = "{\n"
                + "  \"name\": \"morpheus\",\n"
                + "  \"job\": \"zion resident\"\n"
                + "}";

        UserService.updateUsers(requestBody, 2)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("morpheus"),
                        "job", equalTo("zion resident"));

    }


    @Test
    public void updateUserByPatch() {
        LocalDate date = LocalDate.now();

        String requestBody = "{\n"
                + "  \"name\": \"morpheus\",\n"
                + "  \"job\": \"zion resident\"\n"
                + "}";

        UserUpdateResponse userUpdateResponse = new PojoHelper<UserUpdateResponse>()
                .customExtract(UserService.updateUserByPatch(requestBody, 2),
                        UserUpdateResponse.class);

       UserService.updateUserByPatch(requestBody, 2)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("morpheus"),
                        "job", equalTo("zion resident"));

        Assert.assertEquals(userUpdateResponse.getUpdatedAt().substring(0,10), date.toString());
    }


    @Test
    public void deleteUser() {

        Response response = BaseService.delete(String.format(DELETE_USER_URL, 2));
        assertEquals(response.statusCode(), 204);
        System.out.println("Response: " + response.asString());
        System.out.println("Status Code: " + response.statusCode());
    }
}


