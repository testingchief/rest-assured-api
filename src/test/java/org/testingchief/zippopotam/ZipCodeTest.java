package org.testingchief.zippopotam;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ZipCodeTest {

    @Test(priority = 1)
    public void requestZipCode() {
        given().when().
                    get("http://api.zippopotam.us/us/90210").
                then().
                    statusCode(200).
                    assertThat().
                    body("places[0].'place name'", equalTo("Beverly Hills"));
    }
}
