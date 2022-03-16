package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class LoginTest {

    CourierClient courierClient;
    Courier courier;
    int courierID;

    @Before
    public void setUp() {
        courierClient = new CourierClient ();
        courier = Courier.getRandom ();
        courierClient.create (courier);
    }

    @After
    public void tearDown() {
        courierClient.delete (courierID);
    }

    @Test
    @DisplayName("Courier can log in")
    public void courierCanLoginWithValidCredentials() {

        ValidatableResponse loginResponse = courierClient.login (new CourierCredentials (courier.getLogin (), courier.getPassword ()));
        int statusCode = loginResponse.extract ().statusCode ();
        courierID = loginResponse.extract ().path ("id");

        assertThat ("Courier cannot login", statusCode, equalTo (SC_OK));
        assertThat ("Courier ID is incorrect", courierID, is (CoreMatchers.not (0)));
    }

}
