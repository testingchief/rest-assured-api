package org.testingchief.parabank;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParabankSetupTest {
    @Test(priority = 1)
    public void shutdownJmsListener() {
        given().when().
                    post("http://192.168.0.182:8080/parabank/services/bank/shutdownJmsListener").
                then().
                    statusCode(204).
                    log().all();
    }

    @Test(priority = 2)
    public void startupJmsListener() {
        given().when().
                    post("http://192.168.0.182:8080/parabank/services/bank/startupJmsListener").
                then().
                    statusCode(204).
                    log().all();
    }

    @Test(priority = 3)
    public void cleanDB() {
        given().when().
                    post("http://192.168.0.182:8080/parabank/services/bank/cleanDB").
                then().
                    statusCode(204).
                    log().all();
    }

    @Test(priority = 4)
    public void initializeDB() {
        given().when().
                    post("http://192.168.0.182:8080/parabank/services/bank/initializeDB").
                then().
                    statusCode(204).
                    log().all();
    }

    @Test(priority = 5)
    public void setParameter() {
        given().when().
                    post("http://192.168.0.182:8080/parabank/services/bank/setParameter/" + "NewParam" + "/" + "1234").
                then().
                    statusCode(204).
                    log().all();
    }

}
