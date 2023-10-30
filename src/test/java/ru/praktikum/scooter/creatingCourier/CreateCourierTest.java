package ru.praktikum.scooter.creatingCourier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest {
    private Courier courier;
    private CourierClient courierClient;

    private int courierId;

    @Before
    public void setUp() {
        courier = CourierParams.randomCourier();
        courierClient = new CourierClient();

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


    @After
    public void clear() {

        courierId =
                courierClient.loginCourier(courier)
                        .extract().path("id");

        courierClient.deleteCourier(courierId);
    }
}


