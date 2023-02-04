package com.swapi.service;

import com.swapi.pojo.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService extends BaseSpecifications {
    public List<UserData> requestGet(String path){
        return  given()
                .when()
                .get(path )
                .then().log().all()
                .extract()
                .body()
                .jsonPath().getList("userData", UserData.class);
    }
}
