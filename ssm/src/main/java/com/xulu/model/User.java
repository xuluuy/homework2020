package com.xulu.model;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 11:43
 * u_id VARCHAR2(32) PRIMARY KEY NOT NULL,
u_username VARCHAR2(64) DEFAULT NULL,
u_password VARCHAR2(64) DEFAULT NULL,
u_salt VARCHAR2(255) DEFAULT NULL,
u_name VARCHAR2(255) DEFAULT NULL,
u_phone VARCHAR2(255) DEFAULT NULL,
u_email VARCHAR2(255) DEFAULT NULL,
create_time
u_enabled VARCHAR2(32) DEFAULT NULL --0 禁用 1启用
 */
public class User {
    private String u_id;
    private String u_username;
    private String u_password;
    private String u_salt;
    private String u_name;
    private String u_phone;
    private String u_email;
    private String u_enabled;
    private String create_time;
    private String u_type;


    private List<AdminRole> roles;

    public String getU_type() {
        return u_type;
    }

    public void setU_type(String u_type) {
        this.u_type = u_type;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_username() {
        return u_username;
    }

    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_salt() {
        return u_salt;
    }

    public void setU_salt(String u_salt) {
        this.u_salt = u_salt;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_enabled() {
        return u_enabled;
    }

    public void setU_enabled(String u_enabled) {
        this.u_enabled = u_enabled;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }
}
