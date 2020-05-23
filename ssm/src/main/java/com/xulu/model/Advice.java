package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-02-05 10:36
advice_id VARCHAR2(32) PRIMARY KEY NOT NULL,
advice_type VARCHAR2(32) DEFAULT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
reg_name VARCHAR2(64) DEFAULT NULL,
staff_id VARCHAR2(32)DEFAULT NULL, --doc_name
diagnostic_code VARCHAR2(32)DEFAULT NULL,--诊断代码
advice_detail VARCHAR2(512)DEFAULT NULL,
create_time DATE DEFAULT NULL,
end_time DATE DEFAULT NULL
 */
public class Advice implements Serializable {
    private String advice_id;
    private String advice_type;
    private String reg_id;
    private String reg_name;
    private String staff_id;
    private String diagnostic_code;
    private String advice_detail;
    private String create_time;
    private String end_time;

    public String getAdvice_id() {
        return advice_id;
    }

    public void setAdvice_id(String advice_id) {
        this.advice_id = advice_id;
    }

    public String getAdvice_type() {
        return advice_type;
    }

    public void setAdvice_type(String advice_type) {
        this.advice_type = advice_type;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getDiagnostic_code() {
        return diagnostic_code;
    }

    public void setDiagnostic_code(String diagnostic_code) {
        this.diagnostic_code = diagnostic_code;
    }

    public String getAdvice_detail() {
        return advice_detail;
    }

    public void setAdvice_detail(String advice_detail) {
        this.advice_detail = advice_detail;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
