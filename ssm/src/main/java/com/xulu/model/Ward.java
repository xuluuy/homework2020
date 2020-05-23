package com.xulu.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author xuluuy
 * @create 2020-01-30 10:23
ward_id VARCHAR2(32)PRIMARY KEY NOT NULL,
ward_name VARCHAR2(64),
dep_name VARCHAR2(32),
bed_type VARCHAR2(32),
total_bed NUMBER, --总床位
remain_bed NUMBER, --剩余床位
bed_price NUMBER(8,2)
 */
public class Ward implements Serializable {
    private String ward_id;
    private String ward_name;
    private String dep_name;
    private String bed_type;
    private double total_bed;
    private double remain_bed;
    private double bed_price;

    public String getWard_id() {
        return ward_id;
    }

    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getBed_type() {
        return bed_type;
    }

    public void setBed_type(String bed_type) {
        this.bed_type = bed_type;
    }

    public double getTotal_bed() {
        return total_bed;
    }

    public void setTotal_bed(double total_bed) {
        this.total_bed = total_bed;
    }

    public double getRemain_bed() {
        return remain_bed;
    }

    public void setRemain_bed(double remain_bed) {
        this.remain_bed = remain_bed;
    }

    public double getBed_price() {
        return bed_price;
    }

    public void setBed_price(double bed_price) {
        this.bed_price = bed_price;
    }
}
