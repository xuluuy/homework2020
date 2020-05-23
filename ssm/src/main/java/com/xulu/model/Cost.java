package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-02-06 15:40
cost_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
cost_amount NUMBER(8,2) DEFAULT NULL,
cost_operator VARCHAR2(32) DEFAULT NULL,--u_name
create_time DATE DEFAULT NULL,
cost_type VARCHAR2(32) DEFAULT '未结算'
 */
public class Cost implements Serializable {
    private String cost_id;
    private String reg_id;
    private double cost_amount;
    private String cost_operator;
    private String create_time;
    private String cost_type;
    private String reg_name;

    private String day_create;
    private String day_end;

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }

    public String getDay_create() {
        return day_create;
    }

    public void setDay_create(String day_create) {
        this.day_create = day_create;
    }

    public String getDay_end() {
        return day_end;
    }

    public void setDay_end(String day_end) {
        this.day_end = day_end;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getCost_id() {
        return cost_id;
    }

    public void setCost_id(String cost_id) {
        this.cost_id = cost_id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }


    public double getCost_amount() {
        return cost_amount;
    }

    public void setCost_amount(double cost_amount) {
        this.cost_amount = cost_amount;
    }


    public String getCost_operator() {
        return cost_operator;
    }

    public void setCost_operator(String cost_operator) {
        this.cost_operator = cost_operator;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
