package com.xulu.aspect;
import com.xulu.common.IDUtil;
import com.xulu.controller.SysAccessLogController;
import com.xulu.service.SysAccessLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName SysAccessLogAspect
 * @Description TODO 系统访问日志切面类
 * @author xuluuy
 * @create 2020-01-11 20:39
 **/

@Component
public class SysAccessLogAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysAccessLogService sysAccessLogService;
    //访问时间
    private Date accessDate;
    //日志信息Map
    private Map<String,Object> logMap = new HashMap<>();
    private Class clazz; //访问类

    public void doBefore(JoinPoint jp){
        clazz = jp.getTarget().getClass();
        if(clazz!= SysAccessLogController.class) {
            //日志id
            logMap.put("logId", IDUtil.getUUID());
            //请求url
            String url = request.getRequestURI().toString();
            logMap.put("accessInterface",url);

            //请求ip
            String ip = request.getRemoteAddr();
            logMap.put("accessIp",ip);
            //请求方式
            String requestType = request.getMethod();
            logMap.put("requestType",requestType);
            //访问时间
            accessDate = new Date();
            logMap.put("accessDate",accessDate);
            //方法参数
            String args = Arrays.toString(jp.getArgs());
             logMap.put("interfaceParams", args);
        }
    }

    public void doAfter(JoinPoint jp){
        if(clazz!= SysAccessLogController.class) {
            //插入日志信息
            sysAccessLogService.saveSysLog(logMap);
        }
    }
}

