package com.xulu.controller;

import com.xulu.model.*;
import com.xulu.service.CostService;
import com.xulu.service.RegistrationService;
import com.xulu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-02-01 11:37
 */
@RestController
public class CostController {
    @Autowired
    private CostService costService;
    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/api/cost/prement/id",produces = "application/json;charset=utf-8")
    public String queryPreid(){
        return costService.queryPreid();
    }
    @RequestMapping(value = "/api/cost/prepayment/create",produces = "application/json;charset=utf-8")
    public void createPrepayment(@RequestBody Prepayment prepayment){
        costService.createPrepayment(prepayment);
    }
    //查询费用列表
    @RequestMapping(value = "/api/cost/query/costList",produces = "application/json;charset=utf-8")
    public List<Cost> queryCostList(){return costService.queryCostList();}
    //根据u_username查询reg_id
    @RequestMapping(value = "/api/cost/query/regId",produces = "application/json;charset=utf-8")
    public Cost queryRegId(@RequestBody User user){
        return userService.queryRegId(user.getU_username());
    }
    //根据reg_id查询患者信息
    @RequestMapping(value = "/api/cost/query/patientInfo",produces = "application/json;charset=utf-8")
    public Registration queryPatientInfo(@RequestBody Registration registration){
        return registrationService.queryPatientInfo(registration.getReg_id());
    }
    //查询费用明细
    @RequestMapping(value = "/api/cost/query/costDetailList",produces = "application/json;charset=utf-8")
    public List<CostDetail> queryCostDetailList(@RequestBody NurseRecord nurseRecord){
        return costService.queryCostDetailList(nurseRecord);
    }
    //查询结算清单
    @RequestMapping(value = "/api/cost/query/Settlement",produces = "application/json;charset=utf-8")
    public Settlement querySettlement(@RequestBody Settlement settlement){
        return costService.querySettlement(settlement);
    }
    //费用结算
    @RequestMapping(value = "/api/cost/settlement/update",produces = "application/json;charset=utf-8")
    public void updateCost(@RequestBody Cost cost){
        costService.updateCost(cost);
    }
}
