package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-02-01 11:35
pre_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
pre_amount NUMBER(8,2),
OPERATOR_uname VARCHAR2(32),
create_time DATE
 */
public class Prepayment implements Serializable {
    private String pre_id;
    private String reg_id;
    private double pre_amount;
    private String operator_uname;
    private String create_time;

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public double getPre_amount() {
        return pre_amount;
    }

    public void setPre_amount(double pre_amount) {
        this.pre_amount = pre_amount;
    }

    public String getOperator_uname() {
        return operator_uname;
    }

    public void setOperator_uname(String operator_uname) {
        this.operator_uname = operator_uname;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
