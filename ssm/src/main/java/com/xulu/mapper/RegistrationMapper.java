package com.xulu.mapper;

import com.xulu.model.*;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-31 18:41
 */
public interface RegistrationMapper {
    //创建入院登记id
    Integer createRegId();
    //添加入院登记
    void create(Registration registration);
    //查询入院列表
    List<Registration> queryReg();
    //更改入院
    void updateReg(Registration registration);
    //通过id查name
    String queryRegNameByRegid(String reg_id);
    //添加档案
    void createArch(Archives arch);
    //查询档案
    List<Archives> queryArch();

    String queryRegIdByUid(String patient_id);
    //根据reg_id查询患者信息
    Registration queryPatientInfo(String reg_id);
    //添加出院记录
    void createOut(Out out);
    //出院列表
    List<Out> queryOut();
    //更新出院
    void updateOut(Out out);
    //更新入院标志
    void updateIn(Registration reg);
    //更新医生
    void updateDoc(Staff staff);
    //获取医生信息
    Staff getDoc(Registration reg);
    //更新床位
    void updateBed(Bed bed);
    //获取医嘱
    List<Advice> queryAdvice(String reg_id);
    //获取药品
    List<DrugDispatch> queryDrug(String reg_id);
    //获取药品名称
    String queryDrugName(String drug_id);
    //获取用户名
    String getUsername(String patient_id);
}
