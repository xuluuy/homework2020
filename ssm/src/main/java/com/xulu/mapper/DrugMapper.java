package com.xulu.mapper;

import com.xulu.model.Drug;
import com.xulu.model.DrugDispatch;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-30 20:38
 */
public interface DrugMapper {
    //查询药品
    List<Drug> queryDrugList();
    //编辑药品
    void updateDrug(Drug drug);
    //查询最大id
    int quaryMaxId();
    //添加药品
    int createDrug(Drug drug);
    //删除药品
    void deleteDrug(String drug_id);
    //添加用药记录
    void createDispatch(DrugDispatch drugDispatch);
    //根据drugid查询药品
    Drug queryDrug(String drug_id);
    //药品数量变化
    void updateDrugQuantity(Drug drug);
    //查询药品分发
    List<DrugDispatch> queryDispatch();

}
