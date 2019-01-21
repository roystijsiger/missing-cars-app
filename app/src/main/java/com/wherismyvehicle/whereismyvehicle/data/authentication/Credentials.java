package com.wherismyvehicle.whereismyvehicle.data.authentication;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
class Credentials {
    private String email;
    private String password;

    Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
