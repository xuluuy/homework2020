package com.xulu.controller;

import com.xulu.model.Drug;
import com.xulu.model.DrugDispatch;
import com.xulu.model.Ward;
import com.xulu.result.Result;
import com.xulu.result.ResultFactory;
import com.xulu.service.DrugService;
import com.xulu.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-29 10:00
 */
@RestController
public class DrugController {
    @Autowired
    private DrugService drugService;

    //查询药品列表
    @RequestMapping(value = "api/drug/drugList",produces = "application/json;charset=utf-8")
    public List<Drug> queryDrugList(){
        return drugService.queryDrugList();
     }
     //修改药品
    @RequestMapping(value = "api/drug/update",produces = "application/json;charset=utf-8")
    public void updateDrug(@RequestBody Drug drug){
        drugService.updateDrug(drug);
    }
    //添加药品
    @RequestMapping(value = "api/drug/add",produces = "application/json;charset=utf-8")
    public Result addDrug (@RequestBody Drug drug) {
        int count = drugService.createDrug(drug);
        if(count>0) {
            return ResultFactory.buildSuccessResult("ok");
        }else {
            String message = "创建失败！";
            return ResultFactory.buildFailResult(message);
        }
    }
    //删除药品
    @RequestMapping(value = "api/drug/delete",produces = "application/json;charset=utf-8")
    public void deleteDrug(@RequestBody Drug drug){
        drugService.deleteDrug(drug.getDrug_id());
    }
    //添加药品分发
    @RequestMapping(value = "api/drug/dispatch/create",produces = "application/json;charset=utf-8")
    public void createDispatch(@RequestBody DrugDispatch drugDispatch){
        drugService.createDispatch(drugDispatch);
    }
    //查询药品分发
    @RequestMapping(value = "api/drug/dispatch/query",produces = "application/json;charset=utf-8")
    public List<DrugDispatch> queryDispatch(){
        return drugService.queryDispatch();
    }
}
