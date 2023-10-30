package ru.praktikum.scooter.orders;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.order.OrderClient;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersListTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();

    }


    @Test
    @DisplayName("Запрашиваем список заказов")
    public void getOrdersList() {
        ValidatableResponse validatableResponse = orderClient.getListOfOrders();
        validatableResponse.assertThat().statusCode(SC_OK).and().body("orders", notNullValue());


    }
}
