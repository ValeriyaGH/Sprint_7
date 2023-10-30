package ru.praktikum.scooter.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierParams {
    public static Courier randomCourier(){
        String login = RandomStringUtils.randomAlphabetic(5,15);
        String password = RandomStringUtils.randomAlphabetic(5,15);
        String firstName = RandomStringUtils.randomAlphabetic(5,15);
        return new Courier(login,password, firstName);
    }
    public static Courier randomCourierNoPassword(){
        String login = RandomStringUtils.randomAlphabetic(5,15);
        String firstName = RandomStringUtils.randomAlphabetic(5,15);
        return new Courier(login,null, firstName);
    }
    public static Courier randomCourierNoLogin(){
        String password = RandomStringUtils.randomAlphabetic(5,15);
        String firstName = RandomStringUtils.randomAlphabetic(5,15);
        return new Courier(null,password, firstName);
    }
    public static Courier randomCourierNoName(){
        String password = RandomStringUtils.randomAlphabetic(5,15);
        String login = RandomStringUtils.randomAlphabetic(5,15);
        return new Courier(login ,password, null);
    }
    public static Courier createdCourier(){
        String login = "КурьерДляТеста";
        String password = "Qwerty";
        String firstName = "Тестировочный";
        return new Courier(login, password, firstName);

    }

}
