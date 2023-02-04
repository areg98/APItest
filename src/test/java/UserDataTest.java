import com.swapi.pojo.UserData;
import com.swapi.service.BaseSpecifications;
import com.swapi.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.swapi.constant.Urls.USER_URL;
import static com.swapi.utils.Configurations.URL;
import static io.restassured.RestAssured.given;

public class UserDataTest {
    String basePath = String.format(USER_URL, 2);

    @Test
    public void checkAvatarAndId() {
        UserService userService = new UserService();
        BaseSpecifications.InstallSpecification(BaseSpecifications.requestSpec(URL), BaseSpecifications.responseSpecOk200());
        List<UserData> userData = userService.requestGet(basePath);


        userData.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(userData.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));


    }
}
