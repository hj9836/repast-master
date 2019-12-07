package com.aaa.repast.admin.project.system.shopInfo.service;

import com.aaa.repast.admin.project.system.shopInformation.domain.ShopInformation;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;

import java.util.List;

/**
 * 店铺的基本 服务层
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
public interface IShopInfoService
{
	/**
     * 查询店铺的基本信息
     * 
     * @param id 店铺的基本ID
     * @return 店铺的基本信息
     */
	public ShopInformation selectShopInfoById(Long id);
	
	/**
     * 查询店铺的基本列表
     * 
     * @param shopInformation 店铺的基本信息
     * @return 店铺的基本集合
     */
	public List<ShopInformation> selectShopInfoList(ShopInformation shopInformation);
	
	/**
     * 新增店铺的基本
     * 
     * @param shopInformation 店铺的基本信息
     * @return 结果
     */
	public int insertShopInfo(ShopInformation shopInformation,MyRedisService myRedisService, RedisService redisService);
	
	/**
     * 修改店铺的基本
     * 
     * @param shopInformation 店铺的基本信息
     * @return 结果
     */
	public int updateShopInfo(ShopInformation shopInformation,MyRedisService myRedisService, RedisService redisService);
		
	/**
     * 删除店铺的基本信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteShopInfoByIds(String ids,Long shopId,MyRedisService myRedisService, RedisService redisService);
	
}
