package com.aaa.repast.admin.project.system.shopInfo.service;

import com.aaa.repast.admin.project.system.shopInfo.mapper.ShopInfoMapper;
import com.aaa.repast.admin.project.system.shopInformation.domain.ShopInformation;
import com.aaa.repast.admin.project.system.shopInformation.mapper.ShopInformationMapper;
import com.aaa.repast.admin.project.system.shopInformation.service.IShopInformationService;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;
import com.aaa.repast.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店铺的基本 服务层实现
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Service
public class ShopInfoServiceImpl implements IShopInfoService
{
	@Autowired
	private ShopInfoMapper shopInfoMapper;

	/**
     * 查询店铺的基本信息
     * 
     * @param id 店铺的基本ID
     * @return 店铺的基本信息
     */
    @Override
	public ShopInformation selectShopInfoById(Long id)
	{
	    return shopInfoMapper.selectShopInfoById(id);
	}
	
	/**
     * 查询店铺的基本列表
     * 
     * @param shopInformation 店铺的基本信息
     * @return 店铺的基本集合
     */
	@Override
	public List<ShopInformation> selectShopInfoList(ShopInformation shopInformation)
	{
	    return shopInfoMapper.selectShopInfoList(shopInformation);
	}
	
    /**
     * 新增店铺的基本
     * 
     * @param shopInformation 店铺的基本信息
     * @return 结果
     */
	@Override
	public int insertShopInfo(ShopInformation shopInformation, MyRedisService myRedisService, RedisService redisService)
	{
		//新增商品的redis相关操作和修改一样，方法可复用
		System.out.println("这是新增商品方法的数据"+shopInformation);
		Long shopId = shopInformation.getId();
		shopId =1l;
		int i = shopInfoMapper.insertShopInfo(shopInformation);
		//新增redis方法：如果执行行数大于1，则覆盖redis数据
		//因为改变的是店铺菜单信息，所以对应的商品和目录都要进行覆盖，需要首先执行查询方法
		if (i>0){
			boolean b = myRedisService.coverShopInfoByShopIdInRedis(shopId, redisService);
			if (b!=true){
				System.out.println("redis覆盖canteen数据失败！");

			}

		}

		return i;
	}
	
	/**
     * 修改店铺的基本
     * 
     * @param shopInformation 店铺的基本信息
     * @return 结果
     */
	@Override
	public int updateShopInfo(ShopInformation shopInformation,MyRedisService myRedisService, RedisService redisService)
	{
		//新增商品的redis相关操作和修改一样，方法可复用
		System.out.println("这是新增商品方法的数据"+shopInformation);
		Long shopId = shopInformation.getId();
		shopId =1l;
		int i = shopInfoMapper.updateShopInfo(shopInformation);
		//新增redis方法：如果执行行数大于1，则覆盖redis数据
		//因为改变的是店铺菜单信息，所以对应的商品和目录都要进行覆盖，需要首先执行查询方法
		if (i>0){
			boolean b = myRedisService.coverShopInfoByShopIdInRedis(shopId, redisService);
			if (b!=true){
				System.out.println("redis覆盖canteen数据失败！");

			}

		}

		return i;
	}

	/**
     * 删除店铺的基本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteShopInfoByIds(String ids,Long shopId,MyRedisService myRedisService, RedisService redisService)
	{
		//删除如果失败是否能说明redis宕机？不能，有可能是网络传输问题导致的删除失败
		//解决方式：
		//		1、再试一次，加延时器
		//	本次编写暂时不考虑超时情况，默认删除失败为redis宕机，不会造成redis引起的脏读

		Long delstatus = myRedisService.deleteShopInfomationByShopIdInRedis(shopId,redisService);
		System.out.println("service层中删除目录方法查看redis中受影响的行数="+delstatus);
		return shopInfoMapper.deleteShopInfoByIds(Convert.toStrArray(ids));
	}
	
}
