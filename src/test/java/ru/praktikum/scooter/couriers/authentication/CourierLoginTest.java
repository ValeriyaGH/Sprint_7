package ru.praktikum.scooter.couriers.authentication;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
public class CourierLoginTest {
    CourierClient courierClient = new CourierClient();
    Courier courier = CourierParams.randomCourier();
    private static Integer courierId;
    //
    private static Integer CREATED_COURIER_ID = 229718;
    Courier createdCourier = CourierParams.createdCourier();
    @Test
    @DisplayName("Регистрируемся и заходим под только что созданным курьером")
    public void sucsessLoginCourierTest(){
        ValidatableResponse createResponse = courierClient.createCourier(courier);
        ValidatableResponse loginResponse = courierClient.loginCourier(courier);
        courierId = loginResponse.extract().path("id");
        loginResponse.assertThat().statusCode(SC_OK).body("id", notNullValue());
    }
    @Test
    @DisplayName("Заходим под ранее созданным курьером")
    public void authtorizationCreatedCourier() {

        ValidatableResponse validatableResponse =
                courierClient.loginCourier(createdCourier)
                        .assertThat().statusCode(SC_OK)
                        .and().body("id", equalTo(CREATED_COURIER_ID));
    }
    @After
    public void clear(){
        if(courierId != CREATED_COURIER_ID)
        courierClient.deleteCourier(courierId);
    }
}
