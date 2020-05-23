package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-02-02 9:26
bed_id VARCHAR2(32) PRIMARY KEY NOT NULL,
bed_name VARCHAR2(32) DEFAULT null,
ward_id VARCHAR2(32) DEFAULT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
bed_flag VARCHAR2(32) DEFAULT '0',--0未使用 1使用
doc_id VARCHAR2(32) DEFAULT NULL
 */
public class Bed implements Serializable {
    private String bed_id;
    private String bed_name;
    private String ward_id;
    private String ward_name;
    private String reg_id;
    private String bed_flag;
    private String doc_id;

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getBed_id() {
        return bed_id;
    }

    public void setBed_id(String bed_id) {
        this.bed_id = bed_id;
    }

    public String getBed_name() {
        return bed_name;
    }

    public void setBed_name(String bed_name) {
        this.bed_name = bed_name;
    }

    public String getWard_id() {
        return ward_id;
    }

    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getBed_flag() {
        return bed_flag;
    }

    public void setBed_flag(String bed_flag) {
        this.bed_flag = bed_flag;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }
}
