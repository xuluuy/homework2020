package com.xulu.model;





import java.io.Serializable;

/**
 * @author xuluuy
 * @create 2020-01-11 19:40
 * 员工实体类
 *
staff_id VARCHAR2(32) PRIMARY KEY NOT NULL,---即u_id
staff_name VARCHAR2(32) DEFAULT NULL,  --即u_name
staff_phone VARCHAR2(32)DEFAULT NULL, --即u_phone
dep_name VARCHAR2(128)DEFAULT NULL,
role_name VARCHAR2(32)DEFAULT NULL,  --即r_name_zh
staff_sex VARCHAR2(32) DEFAULT NULL,
position VARCHAR2(32)DEFAULT NULL,--岗位
professional_title VARCHAR2(32)DEFAULT NULL,--职称
staff_flag VARCHAR2(32)DEFAULT NULL,--0空闲 1占用,
create_time DATE DEFAULT NULL
 */
public class Staff implements Serializable {
   private String staff_id;
    private String staff_name;
    private String staff_phone;
    private String dep_name;
    private String role_name;
    private String staff_sex;
    private String position;
    private String professional_title;
    private String staff_flag;
    private String create_time;
    private int patient_num;

    public int getPatient_num() {
        return patient_num;
    }

    public void setPatient_num(int patient_num) {
        this.patient_num = patient_num;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_phone() {
        return staff_phone;
    }

    public void setStaff_phone(String staff_phone) {
        this.staff_phone = staff_phone;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getStaff_sex() {
        return staff_sex;
    }

    public void setStaff_sex(String staff_sex) {
        this.staff_sex = staff_sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfessional_title() {
        return professional_title;
    }

    public void setProfessional_title(String professional_title) {
        this.professional_title = professional_title;
    }

    public String getStaff_flag() {
        return staff_flag;
    }

    public void setStaff_flag(String staff_flag) {
        this.staff_flag = staff_flag;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
