package ru.praktikum.scooter.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.scooter.Config;
import static io.restassured.RestAssured.given;
public class OrderClient {
    private static final String PATH_CREATING = "/api/v1/orders";
    private static final String PATH_GET_ORDERS_LIST = "/api/v1/orders";
    @Step("Создаем заказ")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(Config.getSpec())
                .body(order)
                .when()
                .post(PATH_CREATING)
                .then();
    }

    @Step("Запрашиваем список заказов")
    public ValidatableResponse getListOfOrders() {
        return given()
                .spec(Config.getSpec())
                .get(PATH_GET_ORDERS_LIST)
                .then();
    }
}
