package com.xulu.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-29 10:12
 */
public interface SysAccessLogMapper {
    List<Map<String, Object>> querySysLogList (Map<String, Object> map);
    int saveSysLog(Map<String, Object> map);
}
