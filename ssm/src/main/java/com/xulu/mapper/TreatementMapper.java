package com.xulu.mapper;

import com.xulu.model.Advice;
import com.xulu.model.NurseRecord;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-02-05 10:40
 */
public interface TreatementMapper {
    void createAdvice(Advice advice);
    //查询医嘱
    List<Advice> queryAdvice();
    //查询床位号
    String queryRegBed(String reg_id);
    //添加护理记录
    void createNurse(NurseRecord nurseRecord);
    //查询护理记录
    List<NurseRecord> queryNurseRecord();
    //查询某患者护理时间等数据
    List<NurseRecord> queryCreateTime(String reg_id);
}
