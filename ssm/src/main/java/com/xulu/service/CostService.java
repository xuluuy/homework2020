package com.xulu.service;


import com.xulu.model.*;

import java.util.List;

/**
 * @author xuluuy
 * @create 2020-01-11 20:05
 * 费用业务层接口
 */
public interface CostService {
    //查询添加预缴费表 id
    String queryPreid();
    //添加预缴费
    void createPrepayment(Prepayment prepayment);
    //查询费用列表
    List<Cost> queryCostList();
    //查询费用明细
    List<CostDetail> queryCostDetailList(NurseRecord nurseRecord);
    //查询结算清单
    Settlement querySettlement(Settlement settlement);
    //费用结算
    void updateCost(Cost cost);
}

