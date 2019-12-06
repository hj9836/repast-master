package com.aaa.repast.admin.project.tool.redisTools.service;

import com.aaa.repast.admin.project.system.productCategory.mapper.ProductCategoryMapper;
import com.aaa.repast.admin.project.tool.redisTools.domain.CanTeenDateVo;
import com.aaa.repast.admin.project.tool.redisTools.domain.ShopInfoCutom;
import com.aaa.repast.admin.project.tool.redisTools.mapper.RedisMapper;
import com.aaa.repast.admin.project.tool.redisTools.utils.JSONUtil;
import com.aaa.repast.admin.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import static com.aaa.repast.admin.project.tool.redisTools.staticstatus.StaticProperties.*;

@Service
public class MyRedisService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;//涉及redis需要的sql方法，暂时寄托于目录的mapper，后面更改


    public boolean coverShopMenuAndProductByShopIdInRedis(Long shopId, RedisService redisService){
        //首先进行单查询，从mysql中查询该店铺的Canteen数据
        List<CanTeenDateVo> canteenDateByShopId = productCategoryMapper.getCanteenDateByShopId(shopId);
        String s = JSONUtil.toJsonString(canteenDateByShopId);
        //然后覆盖旧数据
        try{
            String setstatus = redisService.set(REDIS_SHOPMENUANDPRODUCT_KEY + shopId, s);
            if ("OK".equals(setstatus.toUpperCase()))return true;
        }catch (Exception e){
            e.printStackTrace();


        }


        return false;
    }

    public boolean coverShopInfoByShopIdInRedis(Long shopId,RedisService redisService){
        //首先进行单查询，从mysql中查询该店铺的Canteen数据
        ShopInfoCutom shopAllInfoLAOYANG = productCategoryMapper.getShopAllInfoLAOYANG(shopId);
        String s = JSONUtil.toJsonString(shopAllInfoLAOYANG);
        //然后覆盖旧数据
        String setstatus = redisService.set(REDIS_SHOPINFO_KEY + shopId, s);
        if ("OK".equals(setstatus.toUpperCase()))return true;


        return false;
    }



    public Long deleteShopMenuAndProductByShopIdInRedis(Long shopId,RedisService redisService){
        Long delstatus = redisService.del(REDIS_SHOPMENUANDPRODUCT_KEY + shopId);
        if (delstatus>0l){
            return delstatus;
        }


        return 0l;
    }

    public Long deleteShopInfomationByShopIdInRedis(Long shopId,RedisService redisService){
        Long delstatus = redisService.del(REDIS_SHOPINFO_KEY + shopId);
        if (delstatus>0l){
            return delstatus;
        }


        return 0l;
    }
}
