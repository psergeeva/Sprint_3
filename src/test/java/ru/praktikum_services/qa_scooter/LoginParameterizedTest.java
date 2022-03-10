package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith (Parameterized.class)
public class LoginParameterizedTest {

    private final  CourierCredentials courierCredentials;
    private final int expectedStatusCode;
    private final String expectedMessage;

    public LoginParameterizedTest(CourierCredentials courierCredentials, int expectedStatusCode, String expectedMessage) {
        this.courierCredentials = courierCredentials;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters
    public static Object[] getTestData(){
        return new Object[][] {
                {CourierCredentials.from(Courier.getCourierWithLoginAndPassword()), 404, "Учетная запись не найдена"},
                {CourierCredentials.getCourierCredentialsWithInvalidLogin(Courier.getCourierWithLoginAndPassword()), 404, "Учетная запись не найдена"},
                {CourierCredentials.getCourierCredentialsWithInvalidPassword(Courier.getCourierWithLoginAndPassword()), 404, "Учетная запись не найдена"},
                {CourierCredentials.from(Courier.getCourierWithLoginOnly()), 400, "Недостаточно данных для входа"},
                {CourierCredentials.from(Courier.getCourierWithPasswordOnly()), 400, "Недостаточно данных для входа"}
        };
    }

    @Test
    @DisplayName("Courier cannot be logged in with insufficient data")
    public void courierLogInWithInsufficientData(){
        ValidatableResponse response = new CourierClient ().login (courierCredentials);

        int actualStatusCode = response.extract ().statusCode ();
        String actualMessage = response.extract ().path("message");

        Assert.assertEquals (expectedStatusCode,actualStatusCode);
        Assert.assertEquals (expectedMessage,actualMessage);
    }


}
