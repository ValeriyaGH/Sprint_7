package ru.praktikum.scooter.courier;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import ru.praktikum.scooter.Config;
import static io.restassured.RestAssured.given;
public class CourierClient {
    private static final String PATH_CREATING = "/api/v1/courier";
    private static final String PATH_LOGIN = "/api/v1/courier/login";
    private static final String PATH_DELETE = "/api/v1/courier/";
    Courier courier;
    LoginCourier loginCourier;
    @Before
    public void setUp() {

        loginCourier = new LoginCourier(courier.getLogin(), courier.getPassword());
    }
    @Step("Удаляем созданного курьера")
    public ValidatableResponse deleteCourier(Integer courierId) {
        return given()
                .spec(Config.getSpec())
                .when()
                .delete(PATH_DELETE + courierId)
                .then();
    }
    @Step("Создаем курьера")
    public ValidatableResponse createCourier(Courier courier) {

        return given()
                .spec(Config.getSpec())
                .body(courier)
                .when()
                .post(PATH_CREATING)
                .then();
    }
    @Step("Выполняем вход клиента в систему ")
    public ValidatableResponse loginCourier(Courier courier) {
        loginCourier = new LoginCourier(courier.getLogin(), courier.getPassword());

        return given()
                .spec(Config.getSpec())
                .body(loginCourier)
                .when()
                .post(PATH_LOGIN)
                .then();
    }
}












