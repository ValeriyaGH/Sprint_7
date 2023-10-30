package ru.praktikum.scooter.creatingCourier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class CreateCourierWithoutPasswordTest {
    private Courier courier;
    private CourierClient courierClient;

    private String errorMessageActual;
    private String errorMessageExpected = "Недостаточно данных для создания учетной записи";

    @Before
    public void setUp() {
        courier = CourierParams.randomCourierNoPassword();
        courierClient = new CourierClient();
    }


    @Test
    @DisplayName("Пробуем создать курьера не указывая пароль ")
    public void tryToCreateCourierWithoutPassword() {
        ValidatableResponse validatableResponse =
                courierClient.createCourier(courier);
        errorMessageActual = validatableResponse.extract().path("message");
        validatableResponse.assertThat().statusCode(SC_BAD_REQUEST);
        assertEquals(errorMessageExpected,errorMessageActual);

    }
}
