package com.xulu.service;

import com.xulu.model.Bed;
import com.xulu.model.Staff;
import com.xulu.model.Ward;

import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-11 20:05
 * 病房业务层接口
 */
public interface WardService {
    //查询病房列表
    List<Ward> queryWardList();
    //编辑病房
    void updateWard(Ward ward);
    //添加病房
    int createWard(Ward ward);
    //删除病房
    void deleteWard(String ward_id);
    //查询病床
    List<Bed> qureyBeds(Ward ward);

    List<Ward> queryWardsList();

    List<Ward> quaryBedType(String ward_name);
}

