package com.xulu.service.impl;

import com.xulu.mapper.CostMapper;
import com.xulu.mapper.RegistrationMapper;
import com.xulu.mapper.TreatementMapper;
import com.xulu.model.Advice;
import com.xulu.model.Cost;
import com.xulu.model.CostDetail;
import com.xulu.model.NurseRecord;
import com.xulu.service.TreatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xuluuy
 * @create 2020-02-05 10:39
 */
@Service
public class TreatementServiceImpl implements TreatementService {
    @Autowired
    private TreatementMapper treatementMapper;
    @Autowired
    private CostMapper costMapper;
    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public void createAdvice(Advice advice) {
        treatementMapper.createAdvice(advice);
        //添加费用明细
        Cost cost = costMapper.queryCost(advice.getReg_id());
        CostDetail exp = new CostDetail();
        exp.setCost_id(cost.getCost_id());
        exp.setCharge_code("TR01");
        exp.setCharge_amount(150);
        costMapper.createCostDetail(exp);
    }

    @Override
    public List<Advice> queryAdvice() {
        return treatementMapper.queryAdvice();
    }

    @Override
    public NurseRecord queryPatientDeatails(String reg_id) {
        String reg_name = registrationMapper.queryRegNameByRegid(reg_id);
        String bed_name = treatementMapper.queryRegBed(reg_id);
        NurseRecord record = new NurseRecord();
        record.setBed_id(bed_name);
        record.setReg_name(reg_name);
        return record;
    }

    @Override
    public void createNurse(NurseRecord nurseRecord) {
        treatementMapper.createNurse(nurseRecord);
    }

    @Override
    public List<NurseRecord> queryNurseRecord() {
        List<NurseRecord> nurseRecords = treatementMapper.queryNurseRecord();
        for(NurseRecord nr : nurseRecords){
            String reg_name = registrationMapper.queryRegNameByRegid(nr.getReg_id());
            nr.setReg_name(reg_name);
        }
        return nurseRecords;
    }

    @Override
    public Map<String,Object> queryCreateTime(String reg_id) {
      List<NurseRecord> nurseRecords =  treatementMapper.queryCreateTime(reg_id);
        Map<String,Object> map = new HashMap<>();
        ArrayList create_time=new ArrayList();
        ArrayList p_temperature=new ArrayList();
        ArrayList heart_rate=new ArrayList();
        ArrayList blood_pressure_h =  new ArrayList();
        ArrayList blood_pressure_l =  new ArrayList();
        String blood_pressur = new String();
        ArrayList blood_sugar = new ArrayList();
      for(NurseRecord nr : nurseRecords){
          create_time.add(nr.getCreate_time());
          p_temperature.add(nr.getP_temperature());
          heart_rate.add(nr.getHeart_rate());
          blood_pressur = nr.getBlood_pressure();
          blood_pressure_h.add(blood_pressur.substring(0, blood_pressur.indexOf("/")));
          blood_pressure_l.add(blood_pressur.substring( blood_pressur.indexOf("/")+1,blood_pressur.length())) ;
          blood_sugar.add(nr.getBlood_sugar());

      }
      map.put("create_time",create_time);
      map.put("p_temperature",p_temperature);
      map.put("heart_rate",heart_rate);
      map.put("blood_pressure_l",blood_pressure_l);
      map.put("blood_pressure_h",blood_pressure_h);
      map.put("blood_sugar",blood_sugar);
      return map;
    }
}
