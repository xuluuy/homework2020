package com.xulu.service;

import com.xulu.model.Staff;

import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-11 20:05
 * 员工业务层接口
 */
public interface StaffService {
    //查询员工列表
    List<Staff> queryStaffList();
    //编辑员工
    void updateStaff(Staff staff);
    //根据科室查询医生
    List<Staff> queryDoc(String dep_name);
}

