package com.yk.pojo;

public class AdminInfo {
    private int id;
    private String adminId;
    private String adminName;
    private String adminPassword;
    private String adminPhone;

    public int getId() {
        return id;
    }

    public AdminInfo setId(int id) {
        this.id = id;
        return this;
    }

    public String getAdminId() {
        return adminId;
    }

    public AdminInfo setAdminId(String adminId) {
        this.adminId = adminId;
        return this;
    }

    public String getAdminName() {
        return adminName;
    }

    public AdminInfo setAdminName(String adminName) {
        this.adminName = adminName;
        return this;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public AdminInfo setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
        return this;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public AdminInfo setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
        return this;
    }
}