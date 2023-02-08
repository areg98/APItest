package com.swapi.service;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.swapi.constant.Urls.USER_URL;

public class UserService extends BaseService {
    public static Response getUsers(Integer pageNumber){
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.queryParam("page",pageNumber);
        requestSpecification.basePath(USER_URL);
        return  get(requestSpecification);
    }

    public static Response addUser(String name, String job){
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.queryParam("name",name);
        requestSpecification.queryParam("job", job);
        return  post(requestSpecification);
    }

    public static Response updateUsers(String requestBody, Integer pageNum){
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.body(requestBody);
        return  put(requestSpecification, pageNum);
    }


    public static Response updateUserByPatch(String requestBody, Integer pageNum){
        RequestSpecification requestSpecification = baseConfigRequest();
        requestSpecification.body(requestBody);
        return  patch(requestSpecification, pageNum);
    }

}
