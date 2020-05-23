package com.xulu.controller;

import com.xulu.model.Archives;
import com.xulu.model.Drug;
import com.xulu.model.Out;
import com.xulu.model.Registration;
import com.xulu.result.Result;
import com.xulu.result.ResultFactory;
import com.xulu.service.DrugService;
import com.xulu.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-29 10:00
 */
@RestController
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    //查询添加入院登记--id
    @RequestMapping(value = "api/inout/createRegId",produces = "application/json;charset=utf-8")
    public String createReg(){
      return  registrationService.createReg();
    }
    //添加入院登记
    @RequestMapping(value = "api/inout/in/create",produces = "application/json;charset=utf-8")
    public void createRegistration(@RequestBody Registration registration){
        registrationService.create(registration);
    }
    //查询入院登记列表
    @RequestMapping(value = "api/inout/in/query",produces = "application/json;charset=utf-8")
    public List<Registration> queryReg(){
        return registrationService.queryReg();
    }
    //修改入院登记
    @RequestMapping(value = "api/inout/in/update",produces = "application/json;charset=utf-8")
    public void updateReg(@RequestBody Registration registration){
        registrationService.updateReg(registration);
    }
    //根据regid查询patientname /inout/in/queryRegName
    @RequestMapping(value = "api/inout/in/queryRegName",produces = "application/json;charset=utf-8")
    public String queryRegNameByRegid(@RequestBody Registration registration){
        return registrationService.queryRegNameByRegid(registration.getReg_id());
    }
    //档案列表
    @RequestMapping(value = "api/inout/archs/query",produces = "application/json;charset=utf-8")
    public List<Archives> queryArch(){
        return registrationService.queryArch();
    }
    //出院列表
    @RequestMapping(value = "api/inout/out/outList",produces = "application/json;charset=utf-8")
    public List<Out> queryOut(){
        return registrationService.queryOut();
    }
    //更新出院
    @RequestMapping(value = "api/inout/out/update",produces = "application/json;charset=utf-8")
    public void updateOut(@RequestBody Out out){
        registrationService.updateOut(out);
    }
    //档案
    @RequestMapping(value = "api/inout/archives/detail",produces = "application/json;charset=utf-8")
    public Map<String,Object> queryArchives(@RequestBody Registration registration){
        return registrationService.queryArchives(registration.getReg_id());
    }
    //微信档案
    @RequestMapping(value = "api/inout/archives/detail/wx",produces = "application/json;charset=utf-8")
    public Map<String,Object> queryArchivesWx(@RequestParam String reg_id){
        return registrationService.queryArchives(reg_id);
    }
}
