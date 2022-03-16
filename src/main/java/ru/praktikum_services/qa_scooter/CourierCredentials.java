package ru.praktikum_services.qa_scooter;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierCredentials {

    private final String login;
    private final String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials (courier.getLogin (), courier.getPassword ());
    }

    public static CourierCredentials getCourierCredentialsWithInvalidLogin(Courier courier) {
        courier.setLogin (RandomStringUtils.randomAlphabetic (5));
        return new CourierCredentials (courier.getLogin (), courier.getPassword ());
    }

    public static CourierCredentials getCourierCredentialsWithInvalidPassword(Courier courier) {
        courier.setPassword (RandomStringUtils.randomAlphabetic (5));
        return new CourierCredentials (courier.getLogin (), courier.getPassword ());
    }
}