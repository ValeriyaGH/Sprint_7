package ru.praktikum.scooter.authorizationCourier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)

public class LoginCourierWithWrongDataTest {
    private Courier courier;
    private CourierClient courierClient;
    private int statusCode;
    private final String error;

    private static Courier createdCourierWithoutPassword = new Courier("КурьерДляТеста", null, "Тестировочный");
    private static Courier createdCourierWithoutLogin = new Courier(null, "Qwerty", "Тестировочный");
    private static Courier createdCourierWithWrongPassword = new Courier("КурьерДляТеста", "Qwerty123", "Тестировочный");
    private static Courier createdCourierWithWrongLogin = new Courier("КурьерДляТестаСОпечаткойАупввв", "Qwerty", "Тестировочный");


    public LoginCourierWithWrongDataTest(String error, int statusCode, Courier courier) {
        this.error = error;
        this.courier = courier;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getDataForTryingLogin() {
        return new Object[][]{
                {"Учетная запись не найдена", 404, createdCourierWithWrongPassword},
                {"Учетная запись не найдена", 404, createdCourierWithWrongLogin},
                {"Недостаточно данных для входа", 400, createdCourierWithoutPassword},
                {"Недостаточно данных для входа", 400, createdCourierWithoutLogin},
        };
    }

    @Before
    public void setUp() {

        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Пробуем войти под курьером с невалидными или недостаточными данными")
    @Description("Проверяем что код ответа и сообщение об ошибке соответствует документации")
    public void tryToAuthCourier() {
        ValidatableResponse validatableResponse = courierClient.loginCourier(courier)
                .assertThat().statusCode(statusCode).log().all()
                .and().body("message", equalTo(error));
    }

}


