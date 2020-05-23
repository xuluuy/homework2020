package com.xulu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xulu.mapper.StaffMapper;
import com.xulu.model.Staff;
import com.xulu.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-11 20:10
 * 员工业务层实现类
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffMapper staffMapper;

    @Override
    public List<Staff> queryStaffList() {
        return staffMapper.queryStaffList();
    }

    @Override
    public void updateStaff(Staff staff) {
        staffMapper.updateStaff(staff);
    }

    @Override
    public List<Staff> queryDoc(String dep_name) {
        return staffMapper.queryDoc(dep_name);
    }

   /* @Override
    public Map<String, Object> queryStaffList(Map<String, Object> map) {
        //获取当前页
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        //每页几条记录
        int pageSize = Integer.parseInt(map.get("pageSize").toString());

        PageHelper.startPage(pageNum,pageSize);
        List<Staff> staffList = staffMapper.queryStaffList(map);
        PageInfo pageInfo = new PageInfo(staffList);

        long total = pageInfo.getTotal();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("data",staffList);
        return resultMap;
    }*/




}
