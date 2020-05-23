package com.xulu.service.impl;

import com.xulu.mapper.CostMapper;
import com.xulu.mapper.RegistrationMapper;
import com.xulu.model.*;
import com.xulu.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-02-01 11:40
 */
@Service
public class CostServiceImpl implements CostService {
    @Autowired
    private CostMapper costMapper;
    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public String queryPreid() {
        return Integer.toString(costMapper.queryPreid());
    }

    @Override
    public void createPrepayment(Prepayment prepayment) {
        costMapper.createPrepayment(prepayment);
    }

    @Override
    public List<Cost> queryCostList() {
        List<Cost> cost = costMapper.queryCostList();
        for(Cost c:cost){
            String reg_name = registrationMapper.queryRegNameByRegid(c.getReg_id());
            c.setReg_name(reg_name);
        }
        return cost;
    }

    @Override
    public List<CostDetail> queryCostDetailList(NurseRecord nurseRecord) {
        String end_time = nurseRecord.getCreate_time();
        String reg_id = nurseRecord.getReg_id();
        //在入院登记中根据reg_id查询create_time
        Registration reg = registrationMapper.queryPatientInfo(reg_id);
        Cost cost = new Cost();
        cost.setDay_create(reg.getCreate_time().substring(0,10));
        cost.setDay_end(end_time.substring(0,10));
        //先添加住院期间床位费
        int days = costMapper.queryDays(cost);
        days+=1;
        //查询床位费
        cost = costMapper.queryCost(reg_id);
        //删除之前床位费
        costMapper.deleteBedPrice(cost.getCost_id());
        Ward ward =costMapper.queryPrice(reg);
        double bed_price = ward.getBed_price();
        double charge_amount = bed_price*days;
        CostDetail exp = new CostDetail();
        exp.setCost_id(cost.getCost_id());
        exp.setCharge_code("BED01");
        exp.setCharge_amount(charge_amount);
        costMapper.createCostDetail(exp);
        //通过reg_id查询cost_id再查询费用明细
        List<CostDetail> info = costMapper.queryCostDetail(cost.getCost_id());
        for(CostDetail cd:info){
            if(cd.getCharge_code().equals("DRU01")){
                cd.setCharge_code("药品费用");
            }else if(cd.getCharge_code().equals("TR01")){
                cd.setCharge_code("治疗费用");
            }else{cd.setCharge_code("床位费用");}
        }
        return info;
    }

    @Override
    public Settlement querySettlement(Settlement settlement) {
        String end_time = settlement.getEnd_time();
        String reg_id = settlement.getReg_id();
        NurseRecord nurseRecord = new NurseRecord();
        nurseRecord.setReg_id(reg_id);
        nurseRecord.setCreate_time(end_time);
        List<CostDetail> costDetails = queryCostDetailList(nurseRecord);
        String cost_id = costDetails.get(0).getCost_id();
        Settlement settlement1 = new Settlement();
        settlement1.setCostDetails(costDetails);
        //在入院登记中根据reg_id查询create_time
        Registration reg = registrationMapper.queryPatientInfo(reg_id);
        settlement1.setReg_id(reg_id);
        settlement1.setReg_name(reg.getReg_name());
        settlement1.setReg_sex(reg.getReg_sex());
        settlement1.setDep_name(reg.getDep_name());
        settlement1.setCreate_time(reg.getCreate_time().substring(0,10));
        settlement1.setEnd_time(end_time.substring(0,10));
        Cost cost = new Cost();
        cost.setDay_create(reg.getCreate_time().substring(0,10));
        cost.setDay_end(end_time.substring(0,10));
        //先添加住院期间床位费
        int days = costMapper.queryDays(cost);
        days+=1;
        settlement1.setDays(days);
        settlement1.setPre_payment(reg.getPre_amount());
        double pre = reg.getPre_amount();
        //总金额
        double sum = costMapper.querySum(cost_id);
        settlement1.setCost_amount(sum);
        double supply = pre-sum;
        if(supply>0){
            settlement1.setOut_amount(Math.abs(supply));
            settlement1.setSupply_amount(0);
        }else {
            settlement1.setSupply_amount(Math.abs(supply));
            settlement1.setOut_amount(0);
        }
        return settlement1;
    }

    @Override
    public void updateCost(Cost cost) {
        //费用结算
        costMapper.updateCost(cost);
        //出院时更新费用结算
        costMapper.updateOut(cost);
    }
}
