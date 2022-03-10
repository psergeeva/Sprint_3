package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class OrderCreationTest {

    private final Order order;
    private final int expectedStatusCode;

    public OrderCreationTest(Order order, int expectedStatusCode) {
        this.order = order;
        this.expectedStatusCode = expectedStatusCode;
    }



    @Parameterized.Parameters
    public static Object[] getTestData(){
        return new Object[][] {
                {Order.getOrderWithColor(new String[]{"BLACK"}), 201},
                {Order.getOrderWithColor(new String[]{"GREY"}), 201},
                {Order.getOrderWithColor(new String[]{"BLACK","GREY"}), 201},
                {Order.getOrderWithoutColor(), 201}
        };
    }

    public void tearDown() {
        new OrderClient().cancelOrder (order);}

    @Test
    @DisplayName("Create order with scooters of different colors")
    public void createOrderInDifferentColors(){
        ValidatableResponse response = new OrderClient ().createOrder (order);
        int actualStatusCode = response.extract ().statusCode ();
        int track = response.extract ().path ("track");

        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        assertThat ("Track is null", track, is(not(0)));

    }


}

