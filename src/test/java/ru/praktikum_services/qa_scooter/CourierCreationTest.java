package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class CourierCreationTest {
    CourierClient courierClient;
    Courier courier;
    int courierID;

    @Before
    public void setUp() {
        courierClient = new CourierClient ();
        courier = Courier.getRandom ();
    }

    @After
    public void tearDown() {
        courierClient.delete (courierID);
    }

    @Test
    @DisplayName("Courier can be created")
    public void courierCanBeCreatedWithValidData() {
        ValidatableResponse response = courierClient.create (courier);
        int statusCode = response.extract ().statusCode ();
        boolean isCreated = response.extract ().path ("ok");
        courierID = courierClient.login (CourierCredentials.from (courier)).extract ().path ("id");

        assertThat (statusCode, equalTo (SC_CREATED));
        assertTrue (isCreated);
        assertNotNull (courierID);

    }

    @Test
    @DisplayName("There is no possibility to create couriers with the same login")
    public void courierCannotBeCreatedWithTheSameLogin() {
        courierClient.create (courier);
        courierID = courierClient.login (CourierCredentials.from (courier)).extract ().path ("id");
        courier.setPassword (RandomStringUtils.randomAlphabetic (5));
        courier.setFirstName (RandomStringUtils.randomAlphabetic (5));

        ValidatableResponse response = courierClient.create (courier);

        int statusCode = response.extract ().statusCode ();
        String message = response.extract ().path ("message");

        assertEquals (409, statusCode);
        assertEquals ("Этот логин уже используется. Попробуйте другой.", message);
    }

}
