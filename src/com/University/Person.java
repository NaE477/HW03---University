package com.University;

public abstract class Person {
    private String fullName;
    private int nationalCode;
    private String username;
    private String password;

    protected Person() {
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setNationalCode(int nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public int getNationalCode() {
        return nationalCode;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
