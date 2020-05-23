package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-01-30 20:32
drug_id VARCHAR2(32)PRIMARY KEY NOT NULL,
drug_name VARCHAR2(64),
drug_price NUMBER(8,2),
drug_supply VARCHAR2(64),
drug_quantity NUMBER,
drug_exp DATE,--有效期
drug_operator VARCHAR2(32)
 */
public class Drug implements Serializable {
    private String drug_id;
    private String drug_name;
    private double drug_price;
    private String drug_supply;
    private double drug_quantity;
    private String drug_exp;
    private String drug_operator;

    public String getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(String drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public double getDrug_price() {
        return drug_price;
    }

    public void setDrug_price(double drug_price) {
        this.drug_price = drug_price;
    }

    public String getDrug_supply() {
        return drug_supply;
    }

    public void setDrug_supply(String drug_supply) {
        this.drug_supply = drug_supply;
    }

    public double getDrug_quantity() {
        return drug_quantity;
    }

    public void setDrug_quantity(double drug_quantity) {
        this.drug_quantity = drug_quantity;
    }

    public String getDrug_exp() {
        return drug_exp;
    }

    public void setDrug_exp(String drug_exp) {
        this.drug_exp = drug_exp;
    }

    public String getDrug_operator() {
        return drug_operator;
    }

    public void setDrug_operator(String drug_operator) {
        this.drug_operator = drug_operator;
    }
}
