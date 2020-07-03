package com.example.hygeia.entity;

import com.example.hygeia.entity.Task;

import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private int usercredit;

    // 用户的历史任务列表
    protected List<Task> taskList;


    // 构造类，只需要email和pwd
    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserCredit() {
        return usercredit;
    }

    public void setUsercreditC(int credit) {
        this.usercredit = usercredit;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
