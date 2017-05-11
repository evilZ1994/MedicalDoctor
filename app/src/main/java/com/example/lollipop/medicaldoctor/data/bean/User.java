package com.example.lollipop.medicaldoctor.data.bean;

import java.util.Date;

/**
 * Created by Lollipop on 2017/5/7.
 */

public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String hospital;
    private String department;

    public User() {
        super();
    }

    //所有参数
    public User(int id, String name, String username, String password, String hospital, String department) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.hospital = hospital;
        this.department = department;
    }

    //没有id
    public User(String name, String username, String password, String hospital, String department) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.hospital = hospital;
        this.department = department;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
