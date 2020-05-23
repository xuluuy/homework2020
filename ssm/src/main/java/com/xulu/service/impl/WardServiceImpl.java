package com.xulu.service.impl;

import com.xulu.mapper.WardMapper;
import com.xulu.model.Bed;
import com.xulu.model.Ward;
import com.xulu.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @author xuluuy
 * @create 2020-01-30 10:20
 */
@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardMapper wardMapper;

    @Override
    public List<Ward> queryWardList() {
        return wardMapper.queryWardList();
    }

    @Override
    public void updateWard(Ward ward) {
        wardMapper.updateWard(ward);
        Bed bed = new Bed();
        bed.setWard_id(ward.getWard_id());
        //删除该病房所有床位
        wardMapper.deleteBed(ward.getWard_id());
        double b =  Double.valueOf(ward.getTotal_bed());

        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        String c = decimalFormat.format(b);
        int total = Integer.parseInt(c);
        for(int i = 1;i<=total;i++){
            String bed_name = ""+total+"-"+i;
            bed.setBed_name(bed_name);
            wardMapper.createBed(bed);
        }

    }

    @Override
    public int createWard(Ward ward) {
        int a = wardMapper.quaryMaxId();
            a +=1;
        String ward_id = Integer.toString(a);
        ward.setWard_id(ward_id);
        int count = wardMapper.createWard(ward);
        double b =  Double.valueOf(ward.getTotal_bed());
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        String c = decimalFormat.format(b);
        int total = Integer.parseInt(c);
        Bed bed = new Bed();
        bed.setWard_id(ward_id);
       for(int i = 1;i<=total;i++){
           String bed_name = ""+total+"-"+i;
           bed.setBed_name(bed_name);
           wardMapper.createBed(bed);
       }

        return count;
    }

    @Override
    public void deleteWard(String ward_id) {
        wardMapper.deleteWard(ward_id);
        wardMapper.deleteBed(ward_id);
    }

    @Override
    public List<Bed> qureyBeds(Ward ward) {
        String ward_id = wardMapper.queryWard_Id(ward);
        return wardMapper.queryBed(ward_id);
    }

    @Override
    public List<Ward> queryWardsList() {
        return  wardMapper.queryWardsList();
    }

    @Override
    public List<Ward> quaryBedType(String ward_name) {
        return wardMapper.quaryBedType(ward_name);
    }
}
