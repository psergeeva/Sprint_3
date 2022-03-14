package ru.praktikum_services.qa_scooter;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    private String login;
    private String password;
    private String firstName;

    public Courier(){

    }

    public Courier(String login,String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public Courier setLogin(String login) {
        this.login = login;
        return null;
    }

    public String getPassword() {
        return password;
    }

    public Courier setPassword(String password) {
        this.password = password;
        return null;
    }

    public String getFirstName() {
        return firstName;
    }

    public Courier setFirstName(String firstName) {
        this.firstName = firstName;
        return null;
    }

    public static Courier getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }

    public static Courier getCourierWithLoginOnly(){
        return new Courier().setLogin(RandomStringUtils.randomAlphabetic(10));
    }
    public static Courier getCourierWithPasswordOnly(){
        return new Courier().setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    public static Courier getCourierWithFirstnameOnly(){
        return new Courier().setFirstName(RandomStringUtils.randomAlphabetic(10));
    }

    public static Courier getCourierWithLoginAndPassword(){
        return new Courier().setLogin(RandomStringUtils.randomAlphabetic(10)).setPassword(RandomStringUtils.randomAlphabetic(10));
    }


}
