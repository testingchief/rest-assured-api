package org.testingchief.parabank;

import org.testng.annotations.Test;
import com.github.javafaker.*;

import static io.restassured.RestAssured.given;

public class LoginTest {
    private static final String BASE_URL = "http://192.168.0.182:8080/parabank/services/bank/";

    @Test
    public void Login() {
        cleanDB();
        initializeDB();
        String userName = "john";
        String password = "demo";
        given().param("username", userName).param("password", password)
                .when().get(BASE_URL + "login")
                .then().statusCode(200)
                .log().all();
    }

    public void cleanDB() {
        given().when().post(BASE_URL + "cleanDB")
                .then().statusCode(204)
                .log().all();
    }

    public void initializeDB() {
        given().when().post(BASE_URL + "initializeDB")
                .then().statusCode(204)
                .log().all();
    }

}
