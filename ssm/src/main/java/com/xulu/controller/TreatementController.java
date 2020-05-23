package com.xulu.controller;

import com.xulu.model.Advice;
import com.xulu.model.NurseRecord;
import com.xulu.model.Prepayment;
import com.xulu.result.Result;
import com.xulu.service.CostService;
import com.xulu.service.TreatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-02-01 11:37
 */
@RestController
public class TreatementController {
    @Autowired
    private TreatementService treatementService;
    //添加advice
    @RequestMapping(value = "/api/treatement/advice/create",produces = "application/json;charset=utf-8")
    public void createAdvice(@RequestBody Advice advice){
        treatementService.createAdvice(advice);
    }
    //查询advice
    @RequestMapping(value = "/api/treatement/advice/query",produces = "application/json;charset=utf-8")
    public List<Advice> queryAdvice(){
        return treatementService.queryAdvice();
    }
    //查询患者相关信息
    @RequestMapping(value = "/api/treatement/nurse/query/patientDeatails",produces = "application/json;charset=utf-8")
    public NurseRecord queryPatientDeatails(@RequestBody NurseRecord nurseRecord){
        return treatementService.queryPatientDeatails(nurseRecord.getReg_id());
    }
    //添加护理记录
    @RequestMapping(value = "/api/treatement/nurse/create",produces = "application/json;charset=utf-8")
    public void createNurse(@RequestBody NurseRecord nurseRecord){
        treatementService.createNurse(nurseRecord);
    }
    //查询护理记录
    @RequestMapping(value = "/api/treatement/nurse/query",produces = "application/json;charset=utf-8")
    public List<NurseRecord> queryNurseRecord(){
        return treatementService.queryNurseRecord();
    }
    //查询某患者护理时间等数据
    @RequestMapping(value = "/api/treatement/nurse/query/createTime",produces = "application/json;charset=utf-8")
    public Map<String,Object> queryCreateTime(@RequestBody NurseRecord nurseRecord){
        return treatementService.queryCreateTime(nurseRecord.getReg_id());
    }
}
