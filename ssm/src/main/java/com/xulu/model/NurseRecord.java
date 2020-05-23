package com.xulu.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuluuy
 * @create 2020-02-07 16:55
nur_id  VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32)DEFAULT NULL,
bed_id VARCHAR2(32)DEFAULT NULL,
staff_id VARCHAR2(32)DEFAULT NULL,
p_temperature VARCHAR2(32)DEFAULT NULL,
heart_rate VARCHAR2(32)DEFAULT NULL,
blood_pressure VARCHAR2(32)DEFAULT NULL,
blood_sugar VARCHAR2(32)DEFAULT NULL,
remarks VARCHAR2(512)DEFAULT NULL,
create_time DATE DEFAULT NULL
 */
public class NurseRecord implements Serializable {
    private String nur_id;
    private String reg_id;
    private String bed_id;
    private String staff_id;
    private String p_temperature;
    private String heart_rate;
    private String blood_pressure;
    private String blood_sugar;
    private String remarks;
    private String create_time;
    private String reg_name;



    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getNur_id() {
        return nur_id;
    }

    public void setNur_id(String nur_id) {
        this.nur_id = nur_id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getBed_id() {
        return bed_id;
    }

    public void setBed_id(String bed_id) {
        this.bed_id = bed_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getP_temperature() {
        return p_temperature;
    }

    public void setP_temperature(String p_temperature) {
        this.p_temperature = p_temperature;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getBlood_sugar() {
        return blood_sugar;
    }

    public void setBlood_sugar(String blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
