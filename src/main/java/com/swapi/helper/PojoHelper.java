package com.swapi.helper;

import com.swapi.pojo.UserUpdateResponse;
import com.swapi.service.UserService;
import io.restassured.response.Response;

public class  PojoHelper <T>{

    public T customExtract(Response response, Class<T> _class){
        return  response
                .then()
                .extract().as(_class);
    }
}
