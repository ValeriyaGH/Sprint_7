package ru.praktikum.scooter.orders;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.scooter.order.Order;
import ru.praktikum.scooter.order.OrderClient;
import ru.praktikum.scooter.order.OrderParams;
import java.util.Collections;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
@RunWith(Parameterized.class)
public class OrderTest {
    private Order order;
    private OrderClient orderClient;
    public OrderTest(Order order){
        this.order = order;
    }
    @Parameterized.Parameters
    public static Object[][] getOrdersData(){
        return new Object[][]{
                {OrderParams.getOrderData(Collections.singletonList("GREY"))},
                {OrderParams.getOrderData(Collections.singletonList("BLACK"))},
                {OrderParams.getOrderData(null)},
                {OrderParams.getOrderData(java.util.List.of("GREY", "BLACK"))},
        };
    }
    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }
    @Test
    @DisplayName("Создаем заказы на самокаты разных цветов и без цвета")
    public void creatingOrderWithDiffrentColors(){
        ValidatableResponse validatableResponse = orderClient.createOrder(order)
                .assertThat().statusCode(SC_CREATED).and().body("track", notNullValue());
    }
    @Test
    @DisplayName("Запрашиваем список заказов")
    public void getOrdersList() {
        ValidatableResponse validatableResponse = orderClient.getListOfOrders();
        validatableResponse.assertThat().statusCode(SC_OK).and().body("orders", notNullValue());
    }
}
