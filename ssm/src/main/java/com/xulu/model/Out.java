package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-02-10 20:41
out_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
is_cost VARCHAR2(32) DEFAULT null,--已结清 未结清
out_OPERATOR VARCHAR2(32) DEFAULT NULL,
create_time DATE DEFAULT NULL
 */
public class Out implements Serializable {
    private String out_id;
    private String reg_id;
    private String is_cost;
    private String out_operator;
    private String create_time;
    private String reg_name;
    private String is_out;

    public String getIs_out() {
        return is_out;
    }

    public void setIs_out(String is_out) {
        this.is_out = is_out;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getOut_id() {
        return out_id;
    }

    public void setOut_id(String out_id) {
        this.out_id = out_id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getIs_cost() {
        return is_cost;
    }

    public void setIs_cost(String is_cost) {
        this.is_cost = is_cost;
    }

    public String getOut_operator() {
        return out_operator;
    }

    public void setOut_operator(String out_operator) {
        this.out_operator = out_operator;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
