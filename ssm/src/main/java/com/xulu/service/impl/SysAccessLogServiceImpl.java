package com.xulu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xulu.mapper.SysAccessLogMapper;
import com.xulu.service.SysAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-29 10:09
 */
@Service
public class SysAccessLogServiceImpl implements SysAccessLogService {
    @Autowired
    private SysAccessLogMapper sysAccessLogMapper;

    @Override
    public Map<String, Object> querySysLogList(Map<String, Object> map) {
        int pageNum=Integer.parseInt(map.get("pageNum").toString()); //当前页
         int pageSize=Integer.parseInt(map.get("pageSize").toString());  //每页几条
         PageHelper.startPage(pageNum,pageSize);
         List<Map<String, Object>> resultList = sysAccessLogMapper.querySysLogList(map);
         PageInfo pageInfo=new PageInfo(resultList);
         long total = pageInfo.getTotal();  //总条数
         Map<String,Object> resultMap=new HashMap<>();
         resultMap.put("total",total);
         resultMap.put("rows",resultList);
         return resultMap;
    }

    @Override
    public int saveSysLog(Map<String, Object> map) {
        return sysAccessLogMapper.saveSysLog(map);
    }
}
