package ru.praktikum.scooter.couriers.create;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest {
    private Courier courierNoPassword = CourierParams.randomCourierNoPassword();
    private Courier courier = CourierParams.randomCourier();
    private CourierClient courierClient;
    private String errorMessageExpectedLoginAlreadyUsed = "Этот логин уже используется";
    private String errorMessageActual;
    private int courierId = 0;
    private String errorMessageExpectedNotEnoughtData = "Недостаточно данных для создания учетной записи";
    boolean isCourierCreated = false;
    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }
    @Test
    @DisplayName("Пробуем создать курьера не указывая пароль")
    public void tryToCreateCourierWithoutPassword() {
        ValidatableResponse validatableResponse =
                courierClient.createCourier(courierNoPassword);
        errorMessageActual = validatableResponse.extract().path("message");
        validatableResponse.assertThat().statusCode(SC_BAD_REQUEST);
        assertEquals(errorMessageExpectedNotEnoughtData,errorMessageActual);
    }
    @Test
    @DisplayName("Создаем курьера ")
    @Description("Проверяем код и тело ответа")
    public void courierCreating() {
        boolean isCourierCreated =
                courierClient.createCourier(courier)
                        .assertThat()
                        .statusCode(SC_CREATED)
                        .extract()
                        .path("ok");
        assertTrue(isCourierCreated);
    }
    @Test
    @DisplayName("Пробуем создать двух курьеров с одинаковым логином и паролем")
    @Description("Проверяем код ответа и сообщение об ошибке ")
    public void tryToCreateCourierWithSameLogin() {
        courierClient.createCourier(courier).assertThat().statusCode(SC_CREATED);
        isCourierCreated = true;
        ValidatableResponse validatableResponse =
                courierClient.createCourier(courier);
        errorMessageActual = validatableResponse.extract().path("message");
        validatableResponse.assertThat().statusCode(SC_CONFLICT);
        assertEquals(errorMessageExpectedLoginAlreadyUsed,errorMessageActual);
         }
    @After
    public void clear(){
        if(isCourierCreated){
            courierId = courierClient.loginCourier(courier)
                    .extract().path("id");
            if (courierId > 0)
                courierClient.deleteCourier(courierId);
        }
    }
}
