package ru.praktikum.scooter.authorizationCourier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class SucsessLoginCourierTest {
    private Courier courier;
    private CourierClient courierClient;
    private static Integer CREATED_COURIER_ID = 229718;
    @Before
    public void setUp() {
        courier = CourierParams.createdCourier();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Заходим под ранее созданным курьером")
    //паталась максимально отвязать эту проверку от проверки создания курьера
    //поэтому отдельно есть метод который проверяет регистрацию и вход под новым и отдельно вход под старым
    public void authtorizationCreatedCourier() {

        ValidatableResponse validatableResponse =
                courierClient.loginCourier(courier)
                        .assertThat().statusCode (SC_OK)
                        .and().body("id", equalTo(229718));

    }

}
