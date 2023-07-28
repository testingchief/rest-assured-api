package org.testingchief.parabank;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest {
    private static final String BASE_URL = "http://192.168.0.182:8080/parabank/services/bank/";

    @Test
    public void Login() {
        cleanDB();
        initializeDB();
        String userName = "john";
        String password = "demo";
        given().when().get(BASE_URL + "login/" + userName + "/" + password)
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
