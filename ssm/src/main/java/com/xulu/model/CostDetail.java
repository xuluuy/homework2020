package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-02-06 15:46
exp_id VARCHAR2(32) PRIMARY KEY NOT NULL,
cost_id VARCHAR2(32) DEFAULT NULL,
charge_code VARCHAR2(32) DEFAULT NULL,--收费代码 预缴费 PRE01 --药品 DRU01 --治疗费 TR01 --床位费 BED01
charge_amount NUMBER(8,2) DEFAULT NULL --金额
 */
public class CostDetail implements Serializable {
    private String exp_id;
    private String cost_id;
    private String charge_code;
    private double charge_amount;

    public String getExp_id() {
        return exp_id;
    }

    public void setExp_id(String exp_id) {
        this.exp_id = exp_id;
    }

    public String getCost_id() {
        return cost_id;
    }

    public void setCost_id(String cost_id) {
        this.cost_id = cost_id;
    }

    public String getCharge_code() {
        return charge_code;
    }

    public void setCharge_code(String charge_code) {
        this.charge_code = charge_code;
    }

    public double getCharge_amount() {
        return charge_amount;
    }

    public void setCharge_amount(double charge_amount) {
        this.charge_amount = charge_amount;
    }
}
