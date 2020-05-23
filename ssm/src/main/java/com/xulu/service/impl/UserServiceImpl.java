package com.xulu.service.impl;


import com.xulu.mapper.MenuMapper;
import com.xulu.mapper.RegistrationMapper;
import com.xulu.mapper.StaffMapper;
import com.xulu.mapper.UserMapper;


import com.xulu.model.*;
import com.xulu.service.MenuService;
import com.xulu.service.UserService;

import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-18 11:57
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public  User  getByUserName(String username) {
        User  user = userMapper.findByUserName(username);
        return user;
    }

    @Override
    public int findByUnameAndUtype(User user) {
        return userMapper.findByUnameAndUtype(user);
    }

    @Override
    public int queryIsEnabled(User user) {
        return userMapper.queryIsEnabled(user);
    }

    @Override
    public boolean isExist(String username) {
        User user = userMapper.findByUserName(username);
        return null!=user;
    }

    @Override
    public int add(User user) {
        int count = userMapper.addUser(user);
        AdminUserRole userRole = new AdminUserRole();
        userRole.setU_id(Integer.toString(userMapper.queryMaxUid()));
        if(user.getU_type().equals("P")){
            userRole.setR_id("5");
        }else {
            userRole.setR_id("");
            Staff staff = new Staff();
            staff.setStaff_id(Integer.toString(userMapper.queryMaxUid()));
            staff.setStaff_name(user.getU_name());
            staff.setStaff_phone(user.getU_phone());
            staffMapper.addStaff(staff);
        }

        int a = userMapper.addUserRole(userRole);
        if(a>0){count++;}
        return count;
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public List<AdminRole> listroles() {
        //所有角色信息
        List<AdminRole> roles = userMapper.listroles();
        /*List<AdminMenu> menus;
        for (AdminRole role : roles) {
            //获取角色所对应的菜单
            menus = menuService.getMenusByRoleId(role.getR_id());

            role.setMenus(menus);
        }*/
        return roles;

    }

    @Override
    public List<AdminUserRole> listUserRole(String u_id) {
        return menuMapper.findAllByUid(u_id);
    }

    @Override
    public int update(User user) {
        int count = userMapper.updateUser(user);
        AdminUserRole userRole = new AdminUserRole();
        userRole.setU_id(user.getU_id());
        List<AdminRole> roles = user.getRoles();
        Staff staff = new Staff();
        staff.setStaff_id(user.getU_id());
        staff.setStaff_name(user.getU_name());
        staff.setStaff_phone(user.getU_phone());
        int a = 0;
        for (AdminRole role : roles) {
            userRole.setR_id(role.getR_id());
            staff.setRole_name(role.getR_name_zh());
            staffMapper.addStaffRolename(staff);
                a = userMapper.updateUserRole(userRole);

        }

        if(a>0){
         count++;
        }
        return count;
    }

    @Override
    public int updatePassword(User requestUser) {
        User user = userMapper.findByUserName(requestUser.getU_username());
        //String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String salt = user.getU_salt();
        String encodedPassword = new SimpleHash("md5", "123456", salt, times).toString();
        user.setU_password(encodedPassword);
        int count = userMapper.updatePassword(user);
        return count;
    }

    @Override
    public int deleteUser(String u_id) {
       int count = userMapper.deleteUser(u_id);
       staffMapper.deleteByUid(u_id);
       menuMapper.deleteAllByUid(u_id);
        return count;
    }

    @Override
    public int updateUstatus(User user) {
        return userMapper.updateUstatus(user);
    }

    @Override
    public int resetPassword(User requestUser) {
        User user = userMapper.findByUserName(requestUser.getU_username());
        int times = 2;
        String salt = user.getU_salt();
        String pass = requestUser.getU_password();
        String encodedPassword = new SimpleHash("md5",pass, salt, times).toString();
        user.setU_password(encodedPassword);
        int count = userMapper.updatePassword(user);
        return count;
    }

    @Override
    public User queryUserByUname(String u_username) {
        return userMapper.findByUserName(u_username);
    }

    @Override
    public Cost queryRegId(String u_username) {
        User user = userMapper.findByUserName(u_username);
        String patient_id = user.getU_id();
        String reg_id = registrationMapper.queryRegIdByUid(patient_id);
        Cost cost = new Cost();
        cost.setReg_id(reg_id);
        return cost;
    }


}
