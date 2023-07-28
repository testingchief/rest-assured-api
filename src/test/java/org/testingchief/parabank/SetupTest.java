package org.testingchief.parabank;

import org.testng.annotations.Test;
import com.github.javafaker.*;

import static io.restassured.RestAssured.given;

public class SetupTest {
    private static final String BASE_URL = "http://192.168.0.182:8080/parabank/services/bank/";

    @Test(priority = 1)
    public void shutdownJmsListener() {
        given().when().post(BASE_URL + "shutdownJmsListener").then()
                .statusCode(204).log().all();
    }

    @Test(priority = 2)
    public void startupJmsListener() {
        given().when().post(BASE_URL + "startupJmsListener").then()
                .statusCode(204).log().all();
    }

    @Test(priority = 3)
    public void cleanDB() {
        given().when().post(BASE_URL + "cleanDB").then().statusCode(204).log()
                .all();
    }

    @Test(priority = 4)
    public void initializeDB() {
        given().when().post(BASE_URL + "initializeDB").then().statusCode(204)
                .log().all();
    }

    @Test(priority = 5)
    public void setParameters() {
        Faker faker = new Faker();
        String productName = faker.company().name();
        String productId = faker.number().digits(6);
        given().when()
                .post(BASE_URL + "setParameter/" + productName + "/" + productId)
                .then().statusCode(204).log().all();
    }

}
