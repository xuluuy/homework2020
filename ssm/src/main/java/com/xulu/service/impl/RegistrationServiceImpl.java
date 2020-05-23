package com.xulu.service.impl;

import com.xulu.mapper.CostMapper;
import com.xulu.mapper.RegistrationMapper;
import com.xulu.mapper.StaffMapper;
import com.xulu.mapper.WardMapper;
import com.xulu.model.*;
import com.xulu.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-31 18:39
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private WardMapper wardMapper;
    @Autowired
    private CostMapper costMapper;

    @Override
    public String createReg() {
        return Integer.toString(registrationMapper.createRegId());
    }

    @Override
    public void create(Registration registration) {
        registrationMapper.create(registration);
        Archives arch = new Archives();
        arch.setReg_id(registration.getReg_id());
        arch.setReg_name(registration.getReg_name());
        arch.setStaff_id(registration.getOperator_uname());
        arch.setCreate_time(registration.getCreate_time());
        registrationMapper.createArch(arch);


    }

    @Override
    public List<Registration> queryReg() {
        return registrationMapper.queryReg();
    }

    @Override
    public void updateReg(Registration registration) {
        registrationMapper.updateReg(registration);
        //医生
        String staff_name = registration.getDoc_name();
        int a = staffMapper.queryPatientNum(staff_name);
        a +=1;
        int patient_num = a;
        Staff staff = new Staff();
        staff.setStaff_name(staff_name);
        staff.setPatient_num(patient_num);
        staffMapper.updatePatientNum(staff);
        //病房
        Ward ward = new Ward();
        ward.setWard_name(registration.getWard_name());
        ward.setBed_type(registration.getBed_type());
        ward = wardMapper.queryward(ward);
        double b =  ward.getRemain_bed();
        b -=1;
        ward.setRemain_bed(b);
        wardMapper.updateRemainBed(ward);
        //病床
        Bed bed = new Bed();
        bed.setReg_id(registration.getReg_id());
        bed.setBed_name(registration.getBed_name());
        bed.setWard_id(ward.getWard_id());
        bed.setDoc_id(registration.getDoc_name());
        wardMapper.updateBedName(bed);
        //添加费用
        Cost cost = new Cost ();
        cost.setReg_id(registration.getReg_id());
        costMapper.createCost(cost);
        //费用明细 ---当查询费用清单时更新
        double bed_price = ward.getBed_price();

        cost = costMapper.queryCost(registration.getReg_id());
        CostDetail exp = new CostDetail();
        exp.setCost_id(cost.getCost_id());
        exp.setCharge_code("BED01");
        exp.setCharge_amount(bed_price);
        costMapper.createCostDetail(exp);
        //添加出院
        Out out = new Out();
        out.setReg_id(registration.getReg_id());
        out.setIs_cost("未结算");
        out.setIs_out("未出院");
        registrationMapper.createOut(out);
    }

    @Override
    public String queryRegNameByRegid(String reg_id) {
        return registrationMapper.queryRegNameByRegid(reg_id);
    }

    @Override
    public List<Archives> queryArch() {
        return registrationMapper.queryArch();
    }

    @Override
    public Registration queryPatientInfo(String reg_id) {
        return registrationMapper.queryPatientInfo(reg_id);
    }

    @Override
    public List<Out> queryOut() {
        List<Out> out = registrationMapper.queryOut();
        for(Out o:out){
            Registration reg = registrationMapper.queryPatientInfo(o.getReg_id());
            o.setReg_name(reg.getReg_name());
        }
        return out;
    }

    @Override
    public void updateOut(Out out) {
        out.setIs_out("已出院");
        //更新出院
        registrationMapper.updateOut(out);
        //更新入院标志
        Registration reg = new Registration();
        reg.setReg_id(out.getReg_id());
        reg.setOut_flag("1");
        registrationMapper.updateIn(reg);
        //更新医生
        reg = registrationMapper.queryPatientInfo(out.getReg_id());
        Staff staff = registrationMapper.getDoc(reg);
        int num = staff.getPatient_num();
        num-=1;
        staff.setPatient_num(num);
        if(num == 0){
            staff.setStaff_flag("0");
            registrationMapper.updateDoc(staff);
        }else{
            registrationMapper.updateDoc(staff);
        }

        //更新床位
        Bed bed = new Bed();
        bed.setReg_id(out.getReg_id());
        bed.setDoc_id("");
        bed.setBed_flag("0");
        registrationMapper.updateBed(bed);
        //病房
        Registration r = registrationMapper.queryPatientInfo(out.getReg_id());
        Ward ward = new Ward();
        ward.setWard_name(r.getWard_name());
        ward.setBed_type(r.getBed_type());
        ward = wardMapper.queryward(ward);
        double b =  ward.getRemain_bed();
        b +=1;
        ward.setRemain_bed(b);
        wardMapper.updateRemainBed(ward);



    }

    @Override
    public Map<String, Object> queryArchives(String reg_id) {
        Map<String,Object> map = new HashMap<>();
        //获取入院登记
        Registration registrations = registrationMapper.queryPatientInfo(reg_id);
        String username = registrationMapper.getUsername(registrations.getPatient_id());
        registrations.setReg_username(username);
        //获取医嘱
        List<Advice> advice = registrationMapper.queryAdvice(reg_id);
        //获取药品
        List<DrugDispatch> drugDispatches = registrationMapper.queryDrug(reg_id);
        for(DrugDispatch d:drugDispatches){
            String drug_name = registrationMapper.queryDrugName(d.getDrug_id());
            d.setDrug_name(drug_name);
        }
        map.put("regs",registrations);
        map.put("advice",advice);
        map.put("drugs",drugDispatches);
        return map;
    }
}
