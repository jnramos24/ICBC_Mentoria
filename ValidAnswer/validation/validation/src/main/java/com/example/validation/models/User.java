package com.example.validation.models;

public class User {
    private Long idName;
    private String name;
    private String email;
    private int age;
    private String password;

    public User() {
    }

    public User(Long idName, String name, String email, int age, String password) {
        this.idName = idName;
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public Long getIdName() {
        return idName;
    }

    public void setIdName(Long idName) {
        this.idName = idName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
