package com.swapi.service;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
        requestSpecification.queryParam("name", name);
        requestSpecification.queryParam("job", job);
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

    public static Response updateUsers(String requestBody, Integer pageNum) {
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.body(requestBody);
        return put(requestSpecification, pageNum);
    }

    public static Response updateUserByPatch(String requestBody, Integer pageNum) {
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.body(requestBody);
        return patch(requestSpecification, pageNum);
    }

    public static Response register(String email, String pass) {
        String requestBody = "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"password\": \"" + pass + "\"\n" +
                "}";

        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.body(requestBody);
        return post(requestSpecification, REGISTER_URL);
    }

    public static Response register(String email) {

        String requestBody = "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "}";

        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.body(requestBody);
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
