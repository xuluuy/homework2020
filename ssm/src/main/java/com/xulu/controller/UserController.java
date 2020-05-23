package com.xulu.controller;

import com.xulu.model.AdminMenu;
import com.xulu.model.AdminRole;
import com.xulu.model.AdminUserRole;
import com.xulu.model.User;
import com.xulu.result.Result;
import com.xulu.result.ResultFactory;
import com.xulu.service.MenuService;
import com.xulu.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 14:26
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/api/admin/user",produces = "application/json;charset=utf-8")
    public List<User> listUsers() {
        List<User> user = userService.list();

        return user;
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/role",produces = "application/json;charset=utf-8")
    public List<AdminRole> listRoles() {
        return userService.listroles();
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/getU_R",produces = "application/json;charset=utf-8")
    public List<AdminUserRole> listUserRole(@RequestBody AdminUserRole userRole) {
        return userService.listUserRole(userRole.getU_id());
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/updateUser",produces = "application/json;charset=utf-8")
    public Result listUserRole(@RequestBody User requestUser) {

        int count = userService.update(requestUser);
        if(count==2) {
            return ResultFactory.buildSuccessResult("ok");
        }else {
            String message = "用户更新失败，请重新更新";
            return ResultFactory.buildFailResult(message);
        }

    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/user/password",produces = "application/json;charset=utf-8")
    public Result resetPassword(@RequestBody User requestUser) {
            userService.updatePassword(requestUser);
            String message = "重置密码成功";
            return ResultFactory.buildSuccessResult(message);


    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/deleteUser",produces = "application/json;charset=utf-8")
    public int deleteUser(@RequestBody User requestUser) {
        int count = userService.deleteUser(requestUser.getU_id());
        return count;
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/user/status",produces = "application/json;charset=utf-8")
    public int updateUstatus(@RequestBody User requestUser) {
        int count = userService.updateUstatus(requestUser);
        return count;
    }

    @RequestMapping(value = "/api/admin/user/query",produces = "application/json;charset=utf-8")
    public User queryUserByUname(@RequestBody User user){
        return userService.queryUserByUname(user.getU_username());
    }


    @RequestMapping(value = "/api/queryUname",produces = "application/json;charset=utf-8")
    public User queryUname(@RequestBody User user){
        return userService.getByUserName(user.getU_name());
    }
}

