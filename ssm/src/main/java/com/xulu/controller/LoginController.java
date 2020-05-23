package com.xulu.controller;


import com.xulu.model.User;
import com.xulu.result.Result;
import com.xulu.result.ResultFactory;
import com.xulu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import org.apache.shiro.authc.AuthenticationException;
/**
 * @author xuluuy
 * @create 2020-01-18 11:52
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    //注册
    @CrossOrigin
    @RequestMapping(value = "api/register",produces = "application/json;charset=utf-8")

    public Result register(@RequestBody User user) {
        String u_username = user.getU_username();
        String u_password = user.getU_password();
        u_username = HtmlUtils.htmlEscape(u_username);
        user.setU_username(u_username);

        boolean exist = userService.isExist(u_username);
        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", u_password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setU_salt(salt);
        user.setU_password(encodedPassword);
       int count = userService.add(user);
       if(count==2) {
           return ResultFactory.buildSuccessResult(user);
       }else {
           String message = "用户注册失败，请重新注册";
           return ResultFactory.buildFailResult(message);
       }
    }
    //登录

    @RequestMapping(value = "api/login",produces = "application/json;charset=utf-8")

    public Result login(@RequestBody User requestUser) {
        //判断用户类型是否为员工
        int count = userService.findByUnameAndUtype(requestUser);
        if(count<1) {
            String message = "请在患者窗口登录！";
            return ResultFactory.buildFailResult(message);
        }
        //判断用户是否禁用
        int num = userService.queryIsEnabled(requestUser);
        if(num<1) {
            String message = "该用户已禁用！";
            return ResultFactory.buildFailResult(message);
        }
        //获取输入id
        String u_username = requestUser.getU_username();
        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(10000);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(u_username, requestUser.getU_password());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(usernamePasswordToken);
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return ResultFactory.buildFailResult(message);
        }
    }
    //登出
    @CrossOrigin
    @RequestMapping(value = "api/logout",produces = "application/json;charset=utf-8")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }
    //认证

    @RequestMapping(value = "api/authentication",produces = "application/json;charset=utf-8")
    public String authentication(){
        return "身份认证成功";
    }

    //密码验证
    @RequestMapping(value = "api/checkUser",produces = "application/json;charset=utf-8")

    public Result checkUser(@RequestBody User requestUser) {
        //获取输入id
        String u_username = requestUser.getU_username();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(u_username, requestUser.getU_password());
       // usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            return ResultFactory.buildSuccessResult(usernamePasswordToken);
        } catch (AuthenticationException e) {
            String message = "密码错误";
            return ResultFactory.buildFailResult(message);
        }
    }
    //修改密码
    @RequestMapping(value = "api/resetPassword",produces = "application/json;charset=utf-8")
    public int resetPass(@RequestBody User user){
      int count =  userService.resetPassword(user);
        return count;
    }

}
