package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends ScooterRestClient {

    private static final String ORDER_PATH = "/api/v1/orders/";

    @Step("Get list of orders")
    public ValidatableResponse getAllOrders() {
        return given ()
                .spec (getBaseSpec ())
                .when ()
                .get (ORDER_PATH)
                .then ();
    }

    @Step("Cancel order")
    public ValidatableResponse cancelOrder(Order order) {
        return given ()
                .spec (getBaseSpec ())
                .body (order)
                .when ()
                .get (ORDER_PATH + "cancel")
                .then ()
                .assertThat ()
                .statusCode (200);
    }

    @Step("Create order")
    public ValidatableResponse createOrder(Order order) {
        return given ()
                .spec (getBaseSpec ())
                .body (order)
                .when ()
                .post (ORDER_PATH)
                .then ()
                .assertThat ()
                .statusCode (200);
    }


}
