package com.xulu.service;

import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-29 10:07
 */
public interface SysAccessLogService {
    //查询日志
     Map<String,Object> querySysLogList(Map<String,Object> map);
    //保存日志
    int saveSysLog(Map<String,Object> map);
}
