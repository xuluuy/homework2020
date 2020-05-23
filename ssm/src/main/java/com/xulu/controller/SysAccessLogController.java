package com.xulu.controller;

import com.xulu.result.Result;
import com.xulu.result.ResultFactory;
import com.xulu.service.SysAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
/**
 * @author xuluuy
 * @create 2020-01-29 10:00
 */
@RestController
public class SysAccessLogController {
    @Autowired
    private SysAccessLogService sysAccessLogService;

    @RequestMapping(value = "api/sysLogList",produces = "application/json;charset=utf-8")
    public Result querySysLogList(@RequestBody Map<String,Object> map){
       Map<String, Object> resultMap = sysAccessLogService.querySysLogList(map);
        return ResultFactory.buildSuccessResult(resultMap);
     }
}
