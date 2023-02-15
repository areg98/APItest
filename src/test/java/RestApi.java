import com.swapi.helper.UserHelper;
import com.swapi.pojo.UserCreateResponse;
import com.swapi.pojo.UserResponse;
import com.swapi.service.UserService;
import com.swapi.utils.RandomString;
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
        String name = RandomString.getAlphabeticString(10);
        String job = "leader";
        UserService.addUser(name, job)
                .then()
                .statusCode(201)
                .body("id", isA(String.class));
        UserCreateResponse userCreateResponse = UserHelper.createUser(name, job);
        UserResponse userResponse = UserHelper.getUser(Integer.valueOf(userCreateResponse.getId()));

        Assert.assertEquals(userResponse.getData().getFirstName(), name);

        UserService.deleteUser(Integer.valueOf(userCreateResponse.getId()));




    }


    @Test
    public void updateUser() {

        UserCreateResponse userCreateResponse = UserHelper.createUser(RandomString.getAlphanumericString(10), "leader");

        String name = userCreateResponse.getName();
        String job = "zion resident";
        Integer userID = Integer.valueOf(userCreateResponse.getId());

        UserService.updateUsers(name, job, userID)
                .then()
                .statusCode(200);

        UserResponse userResponse = UserHelper.getUser(userID);

        Assert.assertEquals(userResponse.getData().getFirstName(), name);
        Assert.assertEquals(userResponse.getData().getId(), userID);
        UserService.deleteUser(Integer.valueOf(userCreateResponse.getId()));

    }


    @Test
    public void updateUserByPatch() {
        LocalDate date = LocalDate.now();

        UserCreateResponse userCreateResponse = UserHelper.createUser(RandomString.getAlphanumericString(10), "leader");

        String name = userCreateResponse.getName();
        String job = "zion resident";
        Integer userID = Integer.valueOf(userCreateResponse.getId());

        UserService.updateUserByPatch(name, job, userID)
                .then()
                .log().ifValidationFails()
                .statusCode(200);

        UserResponse userResponse = UserHelper.getUser(userID);

        Assert.assertEquals(userResponse.getData().getFirstName(), name);
        Assert.assertEquals(userResponse.getData().getId(), userID);
        Assert.assertEquals(UserHelper.updateUserByPatch(name, job, userID).getUpdatedAt().substring(0, 10), date.toString());
        UserService.deleteUser(Integer.valueOf(userCreateResponse.getId()));
    }


    @Test
    public void deleteUser() {

        UserCreateResponse userCreateResponse = UserHelper.createUser(RandomString.getAlphanumericString(10), "leader");

        UserService.deleteUser(Integer.valueOf(userCreateResponse.getId()))
                .then()
                .log().ifValidationFails()
                .statusCode(204);
    }

    @Test
    public void registerSuccess() {

        UserService.register("eve.holt@reqres.in", "pistol")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", isA(Integer.class),
                        "token", isA(String.class));

    }

    @Test
    public void registerUnSuccess() {

        UserService.register("eve.holt@reqres.in")
                .then()
                .log().ifValidationFails()
                .statusCode(400);

    }

    @Test
    public void loginSuccess() {

        UserService.login("eve.holt@reqres.in", "pistol")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("token", isA(String.class));

    }

    @Test
    public void loginUnSuccess() {

        UserService.login("peter@klaven")
                .then()
                .log().ifValidationFails()
                .statusCode(400);
//                .body("error", equalTo("Missing password"));

    }

}


