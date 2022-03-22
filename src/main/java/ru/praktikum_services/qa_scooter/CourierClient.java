package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends ScooterRestClient {

    private static final String COUTIER_PATH = "api/v1/courier/";

    @Step("Login with credentials{credentials}")
    public ValidatableResponse login(CourierCredentials credentials) {
        return given ()
                .spec (getBaseSpec ())
                .body (credentials)
                .when ()
                .post (COUTIER_PATH + "login")
                .then ();
    }

    @Step("Create courier")
    public ValidatableResponse create(Courier courier) {
        return given ()
                .spec (getBaseSpec ())
                .body (courier)
                .when ()
                .post (COUTIER_PATH)
                .then ();
    }

    @Step("Delete courier")
    public ValidatableResponse delete(int courierID) {
        return given ()
                .spec (getBaseSpec ())
                .when ()
                .delete (COUTIER_PATH + courierID)
                .then ()
                .assertThat ()
                .statusCode (200);
    }

}
