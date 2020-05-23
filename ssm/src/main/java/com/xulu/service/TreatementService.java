package com.xulu.service;

import com.xulu.model.Advice;
import com.xulu.model.NurseRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-02-05 10:35
 */
public interface TreatementService {
    //添加医嘱
    void createAdvice(Advice advice);
    //查询医嘱
    List<Advice> queryAdvice();
    //查询患者相关信息
    NurseRecord queryPatientDeatails(String reg_id);
    //添加护理记录
    void createNurse(NurseRecord nurseRecord);
    //查询护理记录
    List<NurseRecord> queryNurseRecord();
    //查询某患者护理时间等数据
    Map<String,Object> queryCreateTime(String reg_id);
}
