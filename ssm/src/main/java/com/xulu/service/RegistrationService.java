package com.xulu.service;


import com.xulu.model.Archives;
import com.xulu.model.Out;
import com.xulu.model.Registration;

import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-11 20:05
 * 药品业务层接口
 */
public interface RegistrationService {
    //查询添加入院登记表 id
    String createReg();
    //添加入院登记
    void create(Registration registration);
    //查询入院登记列表
    List<Registration> queryReg();
    //更改入院登记
    void updateReg(Registration registration);
    //通过id查询name
    String queryRegNameByRegid(String reg_id);
    //获取档案列表
    List<Archives> queryArch();
    //根据reg_id查询患者信息
    Registration queryPatientInfo(String reg_id);
    //出院列表
    List<Out> queryOut();
    //更新出院
    void updateOut(Out out);
    //档案
    Map<String,Object> queryArchives(String reg_id);
}

