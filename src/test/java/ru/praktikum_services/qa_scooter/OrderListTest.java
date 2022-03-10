package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

    public class OrderListTest {
        OrderClient orderClient;
        Order order;

        @Before
        public void  setUp(){
            orderClient = new OrderClient ();
        }

        @Test
        @DisplayName("Get order list")
        public  void getOrderList (){
            ValidatableResponse response = orderClient.getAllOrders ();

            int statusCode = response.extract ().statusCode ();
            List<Object> orders = response.extract().jsonPath().getList("orders");
            int sizeListOrders = orders.size();

            List<Object> listOfIdOrders = response.extract().jsonPath().getJsonObject("orders.id");
            int sizeListOfIdOrders = listOfIdOrders.size();

            assertEquals(statusCode, (SC_OK));
            assertFalse (orders.isEmpty ());
            assertEquals (sizeListOfIdOrders,sizeListOrders);

        }


    }

