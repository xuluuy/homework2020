package com.xulu.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuluuy
 * @create 2020-02-10 15:12
 */
public class Settlement  implements Serializable {
    private String reg_id;
    private String reg_name;
    private String reg_sex;
    private String dep_name;
    private String create_time;
    private String end_time;
    private double pre_payment;
    private double out_amount;
    private double cost_amount;//总金额
    private double supply_amount;//应补金额
    private int days;
    private List<CostDetail> costDetails;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    public String getReg_sex() {
        return reg_sex;
    }

    public void setReg_sex(String reg_sex) {
        this.reg_sex = reg_sex;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
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

    public double getPre_payment() {
        return pre_payment;
    }

    public void setPre_payment(double pre_payment) {
        this.pre_payment = pre_payment;
    }

    public double getOut_amount() {
        return out_amount;
    }

    public void setOut_amount(double out_amount) {
        this.out_amount = out_amount;
    }

    public double getCost_amount() {
        return cost_amount;
    }

    public void setCost_amount(double cost_amount) {
        this.cost_amount = cost_amount;
    }

    public double getSupply_amount() {
        return supply_amount;
    }

    public void setSupply_amount(double supply_amount) {
        this.supply_amount = supply_amount;
    }

    public List<CostDetail> getCostDetails() {
        return costDetails;
    }

    public void setCostDetails(List<CostDetail> costDetails) {
        this.costDetails = costDetails;
    }
}
