package com.xulu.controller;

import com.xulu.model.Bed;
import com.xulu.model.Ward;
import com.xulu.result.Result;
import com.xulu.result.ResultFactory;
import com.xulu.service.SysAccessLogService;
import com.xulu.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-29 10:00
 */
@RestController
public class WardController {
    @Autowired
    private WardService wardService;

    //查询病房列表
    @RequestMapping(value = "api/system/ward",produces = "application/json;charset=utf-8")
    public List<Ward> queryWardList(){
        return wardService.queryWardList();
     }
    @RequestMapping(value = "api/system/wards",produces = "application/json;charset=utf-8")
    public List<Ward> queryWardsList(){
        return wardService.queryWardsList();
    }
     //修改病房
    @RequestMapping(value = "api/system/ward/update",produces = "application/json;charset=utf-8")
    public void updateWard(@RequestBody Ward ward){
        wardService.updateWard(ward);
    }
    //添加病房
    @RequestMapping(value = "api/system/ward/add",produces = "application/json;charset=utf-8")
    public Result addWard (@RequestBody Ward ward) {
        int count = wardService.createWard(ward);
        if(count>0) {
            return ResultFactory.buildSuccessResult("ok");
        }else {
            String message = "创建失败！";
            return ResultFactory.buildFailResult(message);
        }
    }
    //删除病房
    @RequestMapping(value = "api/system/ward/delete",produces = "application/json;charset=utf-8")
    public void deleteWard(@RequestBody Ward ward){
        wardService.deleteWard(ward.getWard_id());
    }
    //床位查询
    @RequestMapping(value = "api/ward/bed/query",produces = "application/json;charset=utf-8")
    public List<Bed> queryBeds(@RequestBody Ward ward){
        return wardService.qureyBeds(ward);
    }
    //根据病区查询病床类型
    @RequestMapping(value = "api/ward/bed/bedType",produces = "application/json;charset=utf-8")
    public List<Ward> quaryBedType(@RequestBody Ward ward){
        return wardService.quaryBedType(ward.getWard_name());
    }
}
