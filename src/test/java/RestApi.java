import com.swapi.helper.PojoHelper;
import com.swapi.pojo.UserUpdateResponse;
import com.swapi.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;

public class RestApi {


    @Test
    public void getUsersList() {
        UserService.getUsers(2)
                .then()
                .body("data.avatar", everyItem(isA(String.class)),
                        "data.email", everyItem(endsWith("@reqres.in")));

    }

    @Test
    public void getUser() {
        Integer userID = 2;

        UserService.getSingleUser(userID)
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(userID),
                        "data.email", endsWith("@reqres.in"),
                        "data.first_name", isA(String.class),
                        "data.last_name", isA(String.class));
    }

    @Test
    public void getUnknownUser() {
        Integer userID = 23;

        UserService.getSingleUser(userID)
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    public void getResourcesList() {
        UserService.getResourcesList()
                .then()
                .log().all()
                .body("data.id", everyItem(isA(Integer.class)),
                        "data.name", everyItem(isA(String.class)),
                        "data.year", everyItem(isA(Integer.class)),
                        "data.color", everyItem(startsWith("#")),
                        "data.pantone_value", everyItem(isA(String.class)));

    }

    @Test
    public void getResource() {
        UserService.getSingleResources(2)
                .then()
                .log().all()
                .body("data.id", isA(Integer.class),
                        "data.name", isA(String.class),
                        "data.year", isA(Integer.class),
                        "data.color", startsWith("#"),
                        "data.pantone_value", isA(String.class));

    }

    @Test
    public void getUnknownResource() {
        UserService.getSingleResources(23)
                .then()
                .log().all()
                .statusCode(404);

    }


    @Test
    public void addUser() {
        UserService.addUser("Babken", "leader")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", isA(String.class));

    }


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

        Assert.assertEquals(userUpdateResponse.getUpdatedAt().substring(0, 10), date.toString());
    }


    @Test
    public void deleteUser() {

        UserService.deleteUser(2)
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void registerSuccess() {

        UserService.register("eve.holt@reqres.in", "pistol")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", isA(Integer.class),
                        "token", isA(String.class));;
    }

    @Test
    public void registerUnSuccess() {

        UserService.register("eve.holt@reqres.in")
                .then()
                .log().all()
                .log().body()
                .statusCode(400);

    }

    @Test
    public void loginSuccess() {

        UserService.login("eve.holt@reqres.in", "pistol")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", isA(String.class));

    }

    @Test
    public void loginUnSuccess() {

        UserService.login("peter@klaven")
                .then()
                .log().all()
                .statusCode(400);
//                .body("error", equalTo("Missing password"));

    }

}


