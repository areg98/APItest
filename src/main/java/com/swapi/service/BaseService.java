package com.swapi.service;

import com.swapi.constant.Urls;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.swapi.constant.Urls.CREATE_USER_URL;
import static com.swapi.constant.Urls.UPDATE_USER_URL;
import static com.swapi.utils.Configurations.BASE_URL;
import static io.restassured.RestAssured.given;
import static io.restassured.mapper.ObjectMapperType.GSON;

public class BaseService {

    protected static RequestSpecification baseConfigRequest() {

        return given()
                .baseUri(BASE_URL)
                .config(RestAssured.config())
//                        .objectMapperConfig(new ObjectMapperConfig(GSON)))
                .urlEncodingEnabled(false)
                        .contentType(ContentType.JSON);

    }

    public static Response get(RequestSpecification requestSpecification) {
        requestSpecification.log().all();
        return requestSpecification.get();
    }

    public static Response post(RequestSpecification requestSpecification) {
        return requestSpecification.post(CREATE_USER_URL);
    }

    public static Response put(RequestSpecification requestSpecification, Integer pageNum ) {
        return requestSpecification.put(String.format(UPDATE_USER_URL, pageNum));
    }

    public static Response patch(RequestSpecification requestSpecification, Integer pageNum) {
        return requestSpecification.patch(String.format(UPDATE_USER_URL, pageNum));
    }

    public static Response delete(String deletePath) {

        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .delete(deletePath);
    }
}

