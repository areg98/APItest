package com.swapi.helper;

import io.restassured.response.Response;

import java.util.List;

public class  PojoHelper <T>{

    public static <T> T customExtract(Response response, Class<T> _class){
        return  response
                .then()
                .extract().as(_class);
    }
    public static <T> List<T> customExtract(Response response, Class<T> _class, String name){ // jnjel ogtagorcelov arajin customExtracty u stanal userneri list
        return  response
                .andReturn().jsonPath().getList(name,_class);
    }
}
