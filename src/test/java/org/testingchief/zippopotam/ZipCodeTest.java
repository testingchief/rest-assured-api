package org.testingchief.zippopotam;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ZipCodeTest {
    private static final String BASE_URL = "http://api.zippopotam.us/";

    //simple test
    @Test(priority = 1)
    public void requestUSZipCode() {
        given().
        when().
            get(BASE_URL + "us/90210").
        then().
            statusCode(200).
            assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills")).
            log().all();
    }

    //using path parameters
    @Test(priority = 2)
    public void requestCAZipCode() {
        given().
            pathParam("countryCode", "ca").
            pathParam("zipCode", "L1R").
        when().
            get(BASE_URL + "{countryCode}/{zipCode}").
        then().
            statusCode(200).
            assertThat().
                body("places[0].'place name'", equalTo("Whitby Central")).
            log().all();
    }

    //using data provider
    @Test(priority = 3)
    public void requestUSCityName() {
        given().
        when().
            get("http://api.zippopotam.us/us/oh/dayton").
        then().
            statusCode(200).
            assertThat().
                body("places[0].'place name'", equalTo("Dayton")).
            log().all();
    }

    @Test(priority = 4)
    public void requestCACityName() {
        given().
        when().
            get("http://api.zippopotam.us/ca/on/ajax").
        then().
            statusCode(200).
            assertThat().
                body("places[0].'place name'", containsString("Ajax")).
            log().all();
    }
}
