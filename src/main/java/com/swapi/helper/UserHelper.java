package com.swapi.helper;

import com.swapi.pojo.UserCreateResponse;
import com.swapi.pojo.UserData;
import com.swapi.pojo.UserResponse;
import com.swapi.pojo.UserUpdateResponse;
import com.swapi.service.UserService;

import java.util.List;

import static com.swapi.helper.PojoHelper.customExtract;

public class UserHelper {

    private UserHelper() {
    }

    ;

    public static List<UserData> getUsers(Integer pageNumber) {
//        return UserService.getUsers(pageNumber)
//                .then()
//                .extract()
//                .body()
//                .jsonPath().getList("userData", UserData.class);
        return customExtract(UserService.getUsers(pageNumber),  UserData.class, "userData");
    }


    public static UserUpdateResponse updateUserByPatch(String name, String job, Integer pageNum) {
        return PojoHelper.customExtract(UserService.updateUserByPatch(name, job, pageNum),
                UserUpdateResponse.class);
    }

    public static UserCreateResponse createUser(String name, String job) {
        return PojoHelper.customExtract(UserService.addUser(name, job),
                UserCreateResponse.class);
    }
    public static UserResponse getUser(Integer id) {
        return PojoHelper.customExtract(UserService.getSingleUser(id),
                UserResponse.class);
    }
}