package com.xulu.service.impl;

import com.xulu.mapper.MenuMapper;
import com.xulu.model.AdminMenu;
import com.xulu.model.AdminRoleMenu;
import com.xulu.model.AdminUserRole;
import com.xulu.model.User;
import com.xulu.service.MenuService;
import com.xulu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 14:29
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    UserService userService;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<AdminMenu> getMenusByCurrentUser() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByUserName(username);
        List<AdminUserRole> userRoleList = menuMapper.findAllByUid(user.getU_id());
        List<AdminMenu> menus = new ArrayList<>();
        for (AdminUserRole userRole : userRoleList) {
            List<AdminRoleMenu> roleMenuList = menuMapper.findAllRMByRid(userRole.getR_id());
            for (AdminRoleMenu roleMenu : roleMenuList) {
                menus.add(menuMapper.findMenuById(roleMenu.getM_id()));
            }
        }
        return menus;
    }

    @Override
    public List<AdminMenu> getAllByParentId(String parentId) {
        return menuMapper.findMenuAllByParentId(parentId);
    }

    @Override
    public List<AdminMenu> getMenusByRoleId(String r_id) {
        List<AdminMenu> menus = new ArrayList<>();
        //查询所有角色-菜单中r_id对应的数据
        List<AdminRoleMenu> rms = menuMapper.findAllRMByRid(r_id);
        for (AdminRoleMenu rm : rms) {
            menus.add(menuMapper.findMenuById(rm.getM_id()));
        }
        handleMenus(menus);
        return menus;
    }

    @Override
    public void handleMenus(List<AdminMenu> menus) {
        for (AdminMenu menu : menus) {
            menu.setChildren(getAllByParentId(menu.getM_id()));
        }

        Iterator<AdminMenu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            AdminMenu menu = iterator.next();
            if (!menu.getM_parent_id().equals("0")) {
                iterator.remove();
            }
        }
    }

    @Override
    public void deleteAllByRid(String r_id) {
        menuMapper.deleteAllRMByRid(r_id);
    }

    @Override
    public void updateRoleMenu(AdminRoleMenu ruleMenu) {
        menuMapper.updateRRoleMenu(ruleMenu);
    }



}
