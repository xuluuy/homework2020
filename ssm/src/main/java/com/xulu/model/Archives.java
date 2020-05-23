package com.xulu.model;

import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-02-05 15:54
arch_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
reg_name VARCHAR2(32) DEFAULT NULL,
staff_id VARCHAR2(32) DEFAULT NULL,
create_time DATE DEFAULT NULL
 */
public class Archives implements Serializable {
    private String arch_id;
    private String reg_id;
    private String reg_name;
    private String staff_id;
    private String create_time;

    public String getArch_id() {
        return arch_id;
    }

    public void setArch_id(String arch_id) {
        this.arch_id = arch_id;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
