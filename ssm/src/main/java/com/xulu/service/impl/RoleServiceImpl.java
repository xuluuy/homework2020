package com.xulu.service.impl;

import com.xulu.mapper.MenuMapper;
import com.xulu.mapper.RoleMapper;
import com.xulu.model.AdminRole;
import com.xulu.model.AdminRoleMenu;
import com.xulu.service.RoleService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-23 19:57
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MenuMapper menuMapper;
    @Override
    public int changeRoleStatus(AdminRole role) {
        return roleMapper.changeRoleStatus(role);
    }

    @Override
    public void updateRole(AdminRole updaterole) {
        roleMapper.updateRole(updaterole);
    }

    @Override
    public ArrayList<String> currentMenu(String r_id) {
        //根据r_id 获取所有m_id
        ArrayList<String> mids = new ArrayList<>();
        List<AdminRoleMenu> rms = menuMapper.findAllRMByRid(r_id);
        for (AdminRoleMenu rm : rms) {

            mids.add(rm.getM_id()) ;
        }
        return mids;
    }

    @Override
    public int createRole(AdminRole role) {
        int a = menuMapper.queryMaxRid();
            a +=1;
       String r_id = Integer.toString(a);
        role.setR_id(r_id);
        int count =menuMapper.createRole(role);
        return count;
    }

    @Override
    public int deleteRole(String r_id) {
        int count = roleMapper.deleteRole(r_id);
        menuMapper.deleteAllRMByRid(r_id);
        return count;
    }
}
