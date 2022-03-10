package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith (Parameterized.class)
public class CourierParameterizedTest {

    private final Courier courier;
    private final int expectedStatusCode;
    private final String expectedMessage;

    public CourierParameterizedTest(Courier courier, int expectedStatusCode, String expectedMessage) {
        this.courier = courier;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters
    public static Object[] getTestData() {
        return new Object[][]{
                {Courier.getCourierWithLoginOnly (), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getCourierWithPasswordOnly (), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getCourierWithFirstnameOnly (), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getCourierWithLoginAndPassword (), 400, "Недостаточно данных для создания учетной записи"}
        };
    }

    //firstName is mandatory but in fact is optional
    @Test
    @DisplayName ("Courier cannot be created with insufficient data")
    public void courierCannotBeCreatedWithoutMandatoryFields(){
        ValidatableResponse response = new CourierClient ().create (courier);

        int actualStatusCode = response.extract ().statusCode ();
        String actualMessage = response.extract ().path("message");

        Assert.assertEquals(expectedStatusCode,actualStatusCode);
        Assert.assertEquals (expectedMessage,actualMessage);

    }
}