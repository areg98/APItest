package com.swapi.helper;

import com.swapi.pojo.*;
import com.swapi.service.UserService;
import io.restassured.response.Response;

import java.util.List;

import static com.swapi.helper.PojoHelper.customExtract;

public class UserHelper {

    /** --- prevent object creation --- */
    private UserHelper() {
    }


    public static List<UserData> getUsers(Integer pageNumber) {
        UserListResponse userListResponse = customExtract(UserService.getUsers(pageNumber),  UserListResponse.class);
        return userListResponse.getUserDataList();
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