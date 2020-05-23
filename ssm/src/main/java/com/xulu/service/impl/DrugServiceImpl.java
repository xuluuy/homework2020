package com.xulu.service.impl;

import com.sun.javafx.scene.web.Debugger;
import com.xulu.mapper.CostMapper;
import com.xulu.mapper.DrugMapper;
import com.xulu.model.Cost;
import com.xulu.model.CostDetail;
import com.xulu.model.Drug;
import com.xulu.model.DrugDispatch;
import com.xulu.service.DrugService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-30 20:37
 */
@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugMapper drugMapper;
    @Autowired
    private CostMapper costMapper;

    @Override
    public List<Drug> queryDrugList() {
        return drugMapper.queryDrugList();
    }

    @Override
    public void updateDrug(Drug drug) {
        drugMapper.updateDrug(drug);
    }

    @Override
    public int createDrug(Drug drug) {
        int a = drugMapper.quaryMaxId();
        a +=1;
        String drug_id = Integer.toString(a);
        drug.setDrug_id(drug_id);
        int count = drugMapper.createDrug(drug);
        return count;
    }

    @Override
    public void deleteDrug(String drug_id) {
        drugMapper.deleteDrug(drug_id);
    }

    @Override
    public void createDispatch(DrugDispatch drugDispatch) {
        drugMapper.createDispatch(drugDispatch);
        //药品数量减少
        Drug drug = drugMapper.queryDrug(drugDispatch.getDrug_id());
        double drug_quantity = drug.getDrug_quantity();
        int a = (new Double(drug_quantity)).intValue();
        int b = drugDispatch.getDrug_num();
        a -=b;
        drug_quantity = Double.valueOf(a);
        drug.setDrug_id(drugDispatch.getDrug_id());
        drug.setDrug_quantity(drug_quantity);
        drugMapper.updateDrugQuantity(drug);
        //添加费用明细
        Cost cost = costMapper.queryCost(drugDispatch.getReg_id());
        double price = drugDispatch.getDrug_price();
        int num = drugDispatch.getDrug_num();
        double sum = num * price;
        CostDetail exp = new CostDetail();
        exp.setCost_id(cost.getCost_id());
        exp.setCharge_code("DRU01");
        exp.setCharge_amount(sum);
        costMapper.createCostDetail(exp);
    }

    @Override
    public List<DrugDispatch> queryDispatch() {
        List<DrugDispatch> dispatch = drugMapper.queryDispatch();
        for(DrugDispatch dis : dispatch){
            Drug drug = drugMapper.queryDrug(dis.getDrug_id());
            dis.setDrug_name(drug.getDrug_name());
        }
        return dispatch;
    }
}
