package org.testingchief.zippopotam;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ZipCodeTest {
    private static final String BASE_URL = "http://api.zippopotam.us/";

    // simple test
    @Test(priority = 1)
    public void requestUSZipCode() {
        given().when().get(BASE_URL + "us/90210").then().statusCode(200).assertThat()
                .body("places[0].'place name'", equalTo("Beverly Hills")).log().all();
    }

    // using path parameters
    @Test(priority = 2)
    public void requestCAZipCode() {
        given().pathParam("countryCode", "ca").pathParam("zipCode", "L1R").when()
                .get(BASE_URL + "{countryCode}/{zipCode}").then().statusCode(200).assertThat()
                .body("places[0].'place name'", equalTo("Whitby Central")).log().all();
    }

    // using data provider
    @DataProvider(name = "US_CITIES")
    public Object[][] cityData() {
        return new Object[][] {
                { "us", "45342", "Miamisburg" },
                { "us", "90210", "Beverly Hills" },
                { "ca", "L1R", "Whitby" }
        };
    }

    @Test(priority = 3, dataProvider = "US_CITIES")
    public void requestCityName(String countryCode, String zipCode, String cityName) {
        given().pathParam("countryCode", countryCode)
                .pathParam("zipCode", zipCode)
                .when().get(BASE_URL + "{countryCode}/{zipCode}").then().statusCode(200)
                .assertThat()
                .body("places[0].'place name'", containsString(cityName)).log().all();
    }

}
