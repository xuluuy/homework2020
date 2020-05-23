package com.xulu.model;

/**
 * @author xuluuy
 * @create 2020-01-18 13:52
 * rm_id varchar2(32) PRIMARY KEY NOT NULL,
r_id VARCHAR2(32) DEFAULT NULL,
m_id VARCHAR2(32) DEFAULT NULL
 */
public class AdminRoleMenu {
    private String rm_id;
    private String r_id;
    private String m_id;

    public String getRm_id() {
        return rm_id;
    }

    public void setRm_id(String rm_id) {
        this.rm_id = rm_id;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }
}
