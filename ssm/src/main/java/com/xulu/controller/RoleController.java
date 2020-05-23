package com.xulu.controller;

import com.xulu.model.AdminMenu;
import com.xulu.model.AdminRole;
import com.xulu.model.AdminRoleMenu;
import com.xulu.model.User;
import com.xulu.result.Result;
import com.xulu.result.ResultFactory;
import com.xulu.service.MenuService;
import com.xulu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-23 18:03
 */
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @CrossOrigin
    @RequestMapping(value = "/api/admin/role/menu",produces = "application/json;charset=utf-8")
    public List<AdminMenu> listAllMenus() {
        List<AdminMenu> menus = menuService.getMenusByRoleId("1");
        return menus;
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/role/status",produces = "application/json;charset=utf-8")
    public int changeRoleStatus(@RequestBody AdminRole role) {
        return roleService.changeRoleStatus(role);
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/role/menus",produces = "application/json;charset=utf-8")
    public void updateRoleMenu(@RequestParam String r_id, @RequestBody LinkedHashMap menusIds) {
        menuService.deleteAllByRid(r_id);
        for (Object value : menusIds.values()) {
            for (String mid : (List<String>)value) {
                AdminRoleMenu rm = new AdminRoleMenu();
                rm.setR_id(r_id);
                rm.setM_id(mid);

                menuService.updateRoleMenu(rm);
            }
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/role/update",produces = "application/json;charset=utf-8")
    public void updateRole(@RequestBody AdminRole role){
        roleService.updateRole(role);
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/currentMenu",produces = "application/json;charset=utf-8")
    public ArrayList<String> currentMenu(@RequestBody AdminRole role){
       return roleService.currentMenu(role.getR_id());
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/role/create",produces = "application/json;charset=utf-8")
    public Result createRole(@RequestBody AdminRole role){
       int count = roleService.createRole(role);
       if(count>0) {
           return ResultFactory.buildSuccessResult("创建角色成功！");
       }else {
           String message = "创建失败！";
           return ResultFactory.buildFailResult(message);
       }
    }
    @CrossOrigin
    @RequestMapping(value = "/api/admin/role/delete",produces = "application/json;charset=utf-8")
    public int deleteRole(@RequestBody AdminRole requestRole) {
        int count = roleService.deleteRole(requestRole.getR_id());
        return count;
    }
}
