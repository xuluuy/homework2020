package com.xulu.mapper;

import com.xulu.model.*;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-02-01 11:41
 */
public interface CostMapper {
    //查询预缴费id序列
    int queryPreid();
    //添加预缴费
    void createPrepayment(Prepayment prepayment);
    //添加费用
    void createCost(Cost cost);
    //查询当前costid
    Cost queryCost(String reg_id);
    //添加费用明细
    void createCostDetail(CostDetail costDetail);
    //查询费用列表
    List<Cost> queryCostList();
    //查询住院时间
    int queryDays(Cost cost);
    //根据cost_id查询床位费用明细
    CostDetail queryCostBedDetail(String cost_id);
    //根据cost_id查询费用明细
    List<CostDetail> queryCostDetail(String cost_id);
    //查询床位费
    Ward queryPrice(Registration reg);
    //删除之前床位费
    void deleteBedPrice(String cost_id);
    //计算总价格
    double querySum(String reg_id);
    //费用结算
    void updateCost(Cost cost);
    //更新出院结算
    void updateOut(Cost cost);
}
