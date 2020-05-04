package com.example.w_gson;

import java.util.List;

public class User {
    private String name,age,email;
    private Address address;
    private List<Skill> skill;

    public User(String name, String age, String email, Address address, List<Skill> skill) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.skill = skill;
    }

    public User(String name, String age, String email, Address address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public User(String name, String age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
