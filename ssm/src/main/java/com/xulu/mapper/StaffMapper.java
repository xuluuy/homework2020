package com.xulu.mapper;



import com.xulu.model.Staff;

import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-11 19:40
 * 员工持久层接口
 */

public interface StaffMapper {
    //查询员工列表
    List<Staff> queryStaffList();
    //更改员工
    void updateStaff(Staff staff);



    //添加员工
    void addStaff(Staff staff);
    //添加员工的角色名
   void addStaffRolename(Staff staff);
   //删除员工
    void deleteByUid(String staff_id);

    List<Staff> queryDoc(String dep_name);
    //查询收治患者数
    int queryPatientNum(String staff_name);
    void updatePatientNum(Staff staff);
}
