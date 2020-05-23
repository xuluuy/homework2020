package com.xulu.mapper;

import com.xulu.model.Bed;
import com.xulu.model.Ward;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-30 10:25
 */
public interface WardMapper {
    //查询病房列表
    List<Ward> queryWardList();
    //编辑病房
    void updateWard(Ward ward);
    //查询最大id
    int quaryMaxId();
    //创建病房
    int createWard(Ward ward);
    //删除病房
    void deleteWard(String ward_id);
    //添加床位
    void createBed(Bed bed);
    //根据病房删除床位
    void deleteBed(String ward_id);
    //查询病房id
    String queryWard_Id(Ward ward);
    //根据病房id查病房名
    String queryWnameByid(String ward_id);

    List<Ward> queryWardsList();

    List<Ward> quaryBedType(String ward_name);
    //根据病房id查询所有床位
    List<Bed> queryBed(String ward_id);
    //根据病房名称和床位类型查询病房id和剩余床位
    Ward queryward(Ward ward);
    //更改剩余床位数量
    void updateRemainBed(Ward ward);
    //更改床位信息
    void updateBedName(Bed bed);
}
