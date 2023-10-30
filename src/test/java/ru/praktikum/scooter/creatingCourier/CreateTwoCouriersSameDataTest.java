package ru.praktikum.scooter.creatingCourier;

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

public class CreateTwoCouriersSameDataTest {
    private Courier courier;
    private CourierClient courierClient;
    private String errorMessageExpected = "Этот логин уже используется";

    private String errorMessageActual;
    private int courierId = 0;


    @Before
    public void setUp() {
        courier = CourierParams.randomCourier();
        courierClient = new CourierClient();

    }

    @Test
    @DisplayName("Пробуем создать двух курьеров с одинаковым логином и паролем")
    @Description("Проверяем код ответа и сообщение об ошибке ")

    public void tryToCreateCourierWithSameLogin() {
        courierClient.createCourier(courier).assertThat().statusCode(SC_CREATED);
        ValidatableResponse validatableResponse =
                courierClient.createCourier(courier);
        errorMessageActual = validatableResponse.extract().path("message");
        validatableResponse.assertThat().statusCode(SC_CONFLICT);
        assertEquals(errorMessageExpected,errorMessageActual);

    }

    @After
    public void clear(){
        courierId = courierClient.loginCourier(courier)
                .extract().path("id");
        if(courierId>0)
            courierClient.deleteCourier(courierId);
    }
}
