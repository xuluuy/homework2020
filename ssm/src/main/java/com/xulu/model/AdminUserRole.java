package com.xulu.model;

/**
 * @author xuluuy
 * @create 2020-01-18 13:53
 * ur_id VARCHAR2(32) PRIMARY KEY NOT NULL,
u_id VARCHAR2(32) DEFAULT NULL REFERENCES hims_user(u_id),
r_id VARCHAR2(32) DEFAULT NULL REFERENCES hims_role(r_id)
 */
public class AdminUserRole {
    private String ur_id;
    private String u_id;
    private String r_id;

    public String getUr_id() {
        return ur_id;
    }

    public void setUr_id(String ur_id) {
        this.ur_id = ur_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }
}
