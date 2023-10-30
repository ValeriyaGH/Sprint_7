package ru.praktikum.scooter.order;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class OrderParams {

    public static Order getOrderData(List<String> color) {
        String firstName = RandomStringUtils.randomAlphabetic(10, 15);
        String lastName = RandomStringUtils.randomAlphabetic(10, 15);
        String address = "Москва, ул Лермонтова, 25 квартира 40";

        String metroStation = "Крылатское";
        String phone = "+79777777777";
        String rentTime = "3";
        String deliveryDate = "2023-11-11";
        String comment = "Тестовый заказ! Не нужно приезжать;)";
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}

