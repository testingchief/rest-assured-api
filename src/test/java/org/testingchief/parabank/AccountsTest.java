package org.testingchief.parabank;

import org.testng.annotations.Test;
import com.github.javafaker.*;

import static io.restassured.RestAssured.given;

public class AccountsTest {
    private static final String BASE_URL = "http://192.168.0.182:8080/parabank/services/bank/";
    private static final int CUSTOMER_ID = 12212;

    @Test(priority = 1)
    public void findAccounts() {
        given()
                .when().get(BASE_URL + "customers/" + CUSTOMER_ID + "/accounts")
                .then().assertThat()
                .statusCode(200)
                .body("customerId", CUSTOMER_ID)
                .log().all();
    }

}
