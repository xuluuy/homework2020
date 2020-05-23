package com.xulu.controller;


import com.xulu.model.Staff;
import com.xulu.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-11 20:17
 * 员工控制层
 */
@RestController
public class StaffController {
    @Autowired
    private StaffService staffService;
   //员工查询
    @RequestMapping(value = "/api/admin/staff",produces = "application/json;charset=utf-8")
    public List<Staff> queryStaffList(){
        List<Staff> staff = staffService.queryStaffList();
        return staff;
    }
    //修改员工
    @RequestMapping(value = "/api/admin/staff/update",produces = "application/json;charset=utf-8")
    public void updateStaff(@RequestBody Staff staff){
        staffService.updateStaff(staff);
    }
    //根据科室查询医生
    @RequestMapping(value = "/api/staff/queryDoc",produces = "application/json;charset=utf-8")
    public List<Staff> queryDoc(@RequestBody Staff staff){
        return staffService.queryDoc(staff.getDep_name());
    }

}

