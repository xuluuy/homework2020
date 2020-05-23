package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-02-05 21:07
med_id VARCHAR2(32) PRIMARY KEY NOT NULL,
drug_id VARCHAR2(32) DEFAULT NULL,
drug_num NUMBER DEFAULT NULL,
drug_price NUMBER(8,2) DEFAULT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
staff_id VARCHAR2(32) DEFAULT NULL,
create_time DATE  DEFAULT NULL
 */
public class DrugDispatch implements Serializable {
    private String med_id;
    private String drug_id;
    private String drug_name;
    private int drug_num;
    private double drug_price;
    private String reg_id;
    private String staff_id;
    private String create_time;

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getMed_id() {
        return med_id;
    }

    public void setMed_id(String med_id) {
        this.med_id = med_id;
    }

    public String getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(String drug_id) {
        this.drug_id = drug_id;
    }

    public int getDrug_num() {
        return drug_num;
    }

    public void setDrug_num(int drug_num) {
        this.drug_num = drug_num;
    }

    public double getDrug_price() {
        return drug_price;
    }

    public void setDrug_price(double drug_price) {
        this.drug_price = drug_price;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
