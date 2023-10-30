package ru.praktikum.scooter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import static java.util.Locale.filter;

public class Config {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    public static RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .log(LogDetail.ALL)
                .build();
    }
}
