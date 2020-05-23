package com.xulu.controller;

import com.xulu.model.AdminMenu;
import com.xulu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 14:26
 */
@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    @CrossOrigin
    @RequestMapping(value = "api/menu",produces = "application/json;charset=utf-8")
    public List<AdminMenu> menu() {
        List<AdminMenu> menus = menuService.getMenusByCurrentUser();
        List<String> arr = new ArrayList<>();
        for(AdminMenu me : menus) {
            arr.add(me.getM_id());
        }
        for (AdminMenu menu : menus) {
            List<AdminMenu> mns = menuService.getAllByParentId(menu.getM_id());
            List<AdminMenu> addmns = new ArrayList<>();
            for(AdminMenu mn : mns){
                if(arr.contains(mn.getM_id())){
                    addmns.add(mn);
                }
            }
            menu.setChildren(addmns);
        }

        Iterator<AdminMenu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            AdminMenu menu = iterator.next();

            if (!menu.getM_parent_id().equals("0")) {
               iterator.remove();
            }
        }
        return menus;
    }

}

