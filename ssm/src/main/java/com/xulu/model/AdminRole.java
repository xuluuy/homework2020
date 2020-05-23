package com.xulu.model;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 13:49
 * r_id VARCHAR2(32) PRIMARY KEY NOT NULL,
r_name VARCHAR2(128) DEFAULT NULL,
r_name_zh VARCHAR2(128) DEFAULT NULL,
r_enabled VARCHAR2(32) DEFAULT NULL
 */
public class AdminRole {
    private String r_id;
    private String r_name;
    private String r_name_zh;
    private String r_enabled;

    private List<AdminMenu> menus;

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_name_zh() {
        return r_name_zh;
    }

    public void setR_name_zh(String r_name_zh) {
        this.r_name_zh = r_name_zh;
    }

    public String getR_enabled() {
        return r_enabled;
    }

    public void setR_enabled(String r_enabled) {
        this.r_enabled = r_enabled;
    }

    public List<AdminMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<AdminMenu> menus) {
        this.menus = menus;
    }
}
