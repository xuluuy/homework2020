package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-01-31 11:32
reg_id VARCHAR2(32) PRIMARY KEY NOT NULL,
patient_id VARCHAR2(32) DEFAULT NULL,--u_id
reg_name VARCHAR2(32)DEFAULT NULL,
reg_sex VARCHAR2(32)DEFAULT NULL,
reg_age NUMBER DEFAULT NULL,
reg_birth DATE DEFAULT NULL,
reg_identity VARCHAR2(32) DEFAULT NULL,--身份证
reg_workplace VARCHAR2(64) DEFAULT NULL,--工作地
reg_phone VARCHAR2(32) DEFAULT NULL,--电话
carte_vital VARCHAR2(32) DEFAULT NULL,--医保卡
pre_amount NUMBER(8,2) DEFAULT NULL,--预缴费
dep_name VARCHAR2(32) DEFAULT NULL,
OPERATOR_uname VARCHAR2(32) DEFAULT NULL,
condition VARCHAR2(64) DEFAULT NULL,--病情
out_flag VARCHAR2(32) DEFAULT '0', --0未出院 1出院
ward_name VARCHAR2(128) DEFAULT NULL,
bed_name VARCHAR2(32) DEFAULT NULL,
doc_name VARCHAR2(32)DEFAULT NULL,
create_time DATE DEFAULT NULL
 */
public class Registration implements Serializable {
    private String reg_id;
    private String patient_id;
    private String reg_name;
    private String reg_sex;
    private double reg_age;
    private String reg_birth;
    private String reg_identity;
    private String reg_workplace;
    private String reg_phone;
    private String carte_vital;
    private double pre_amount;
    private String dep_name;
    private String operator_uname;
    private String condition;
    private String out_flag;
    private String create_time;
    private String ward_name;
    private String bed_name;
    private String doc_name;
    private String bed_type;
    private String reg_username;//用户名

    public String getReg_username() {
        return reg_username;
    }

    public void setReg_username(String reg_username) {
        this.reg_username = reg_username;
    }

    public String getBed_type() {
        return bed_type;
    }

    public void setBed_type(String bed_type) {
        this.bed_type = bed_type;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getBed_name() {
        return bed_name;
    }

    public void setBed_name(String bed_name) {
        this.bed_name = bed_name;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getReg_sex() {
        return reg_sex;
    }

    public void setReg_sex(String reg_sex) {
        this.reg_sex = reg_sex;
    }

    public double getReg_age() {
        return reg_age;
    }

    public void setReg_age(double reg_age) {
        this.reg_age = reg_age;
    }

    public String getReg_birth() {
        return reg_birth;
    }

    public void setReg_birth(String reg_birth) {
        this.reg_birth = reg_birth;
    }

    public String getReg_identity() {
        return reg_identity;
    }

    public void setReg_identity(String reg_identity) {
        this.reg_identity = reg_identity;
    }

    public String getReg_workplace() {
        return reg_workplace;
    }

    public void setReg_workplace(String reg_workplace) {
        this.reg_workplace = reg_workplace;
    }

    public String getReg_phone() {
        return reg_phone;
    }

    public void setReg_phone(String reg_phone) {
        this.reg_phone = reg_phone;
    }

    public String getCarte_vital() {
        return carte_vital;
    }

    public void setCarte_vital(String carte_vital) {
        this.carte_vital = carte_vital;
    }

    public double getPre_amount() {
        return pre_amount;
    }

    public void setPre_amount(double pre_amount) {
        this.pre_amount = pre_amount;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getOperator_uname() {
        return operator_uname;
    }

    public void setOperator_uname(String operator_uname) {
        this.operator_uname = operator_uname;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOut_flag() {
        return out_flag;
    }

    public void setOut_flag(String out_flag) {
        this.out_flag = out_flag;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
