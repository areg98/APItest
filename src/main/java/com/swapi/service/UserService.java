package com.swapi.service;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static com.swapi.constant.Urls.*;

public class UserService extends BaseService {

    public static Response getUsers(Integer pageNumber) {
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.basePath(String.format(USER_LIST_URL, pageNumber));
        return get(requestSpecification);
    }

    public static Response getSingleUser(Integer id) {
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.basePath(String.format(USER_URL, id));
        return get(requestSpecification);
    }

    public static Response addUser(String name, String job) {
        RequestSpecification requestSpecification = baseConfigRequest();
        Map<String, String> createUser =  new HashMap<>();
        createUser.put("name", name);
        createUser.put("job", job);
        requestSpecification.body(createUser);
        return post(requestSpecification, CREATE_USER_URL);
    }

    public static Response getResourcesList() {
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.basePath(RESURCES_LIST_URL);
        return get(requestSpecification);
    }

    public static Response getSingleResources(Integer pageNum) {
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.basePath(String.format(SINGLE_RESURCES_URL, pageNum));
        return get(requestSpecification);
    }

    public static Response updateUsers(String name, String job, Integer pageNum) {
        RequestSpecification requestSpecification = baseConfigRequest();
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("name", name);
        jsonBody.put("job", job);
        requestSpecification.body(jsonBody);
        return put(requestSpecification, pageNum);
    }

    public static Response updateUserByPatch(String name, String job, Integer userId) {
        RequestSpecification requestSpecification = baseConfigRequest();
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("name", name);
        jsonBody.put("job", job);
        requestSpecification.body(jsonBody);
        return patch(requestSpecification, userId);
    }

    public static Response register(String email, String pass) {
        RequestSpecification requestSpecification = baseConfigRequest();
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("email", email);
        jsonBody.put("password", pass);
        requestSpecification.body(jsonBody);
        return post(requestSpecification, REGISTER_URL);
    }

    public static Response register(String email) {

        RequestSpecification requestSpecification = baseConfigRequest();
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("email", email);
        requestSpecification.body(jsonBody);
        return post(requestSpecification, REGISTER_URL);
    }

    public static Response deleteUser(Integer userID) {
        RequestSpecification requestSpecification = baseConfigRequest();
        return delete(requestSpecification, userID);
    }

    public static Response login(String email, String pass) {
        String requestBody = "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"password\": \"" + pass + "\"\n" +
                "}";

        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.body(requestBody);
        return post(requestSpecification, LOGIN_URL);
    }

    public static Response login(String email) {

        String requestBody = "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "}";

        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.body(requestBody);
        return post(requestSpecification, LOGIN_URL);
    }

}
