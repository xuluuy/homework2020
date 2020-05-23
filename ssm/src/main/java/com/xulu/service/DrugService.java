package com.xulu.service;

import com.xulu.model.Drug;
import com.xulu.model.DrugDispatch;
import com.xulu.model.Ward;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-11 20:05
 * 药品业务层接口
 */
public interface DrugService {
    //查询病房列表
    List<Drug> queryDrugList();
    //编辑病房
    void updateDrug(Drug drug);
    //添加病房
    int createDrug(Drug drug);
    //删除病房
    void deleteDrug(String drug_id);
    //添加药品分发
    void createDispatch(DrugDispatch drugDispatch);
    //查询药品分发
    List<DrugDispatch> queryDispatch();
}

