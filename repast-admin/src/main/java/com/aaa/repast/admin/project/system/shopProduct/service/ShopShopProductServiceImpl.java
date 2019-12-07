package com.aaa.repast.admin.project.system.shopProduct.service;

import com.aaa.repast.admin.project.system.shopProduct.domain.ShopProduct;
import com.aaa.repast.admin.project.system.shopProduct.mapper.ShopProductMapper;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;
import com.aaa.repast.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品 服务层实现
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Service
public class ShopShopProductServiceImpl implements IShopProductService
{
	@Autowired
	private ShopProductMapper shopProductMapper;

	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
    @Override
	public ShopProduct selectProductById(Long id)
	{
	    return shopProductMapper.selectProductById(id);
	}
	
	/**
     * 查询商品列表
     * 
     * @param shopProduct 商品信息
     * @return 商品集合
     */
	@Override
	public List<ShopProduct> selectProductList(ShopProduct shopProduct)
	{
	    return shopProductMapper.selectProductList(shopProduct);
	}
	
    /**
     * 新增商品
     * 
     * @param shopProduct 商品信息
     * @return 结果
     */
	@Override
	public int insertProduct(ShopProduct shopProduct, MyRedisService myRedisService, RedisService redisService)
	{
		//新增商品的redis相关操作和修改一样，方法可复用
		System.out.println("这是新增商品方法的数据"+shopProduct);
		Long shopId = shopProduct.getShopId();
		shopId =1L;
		int i = shopProductMapper.insertProduct(shopProduct);
		//新增redis方法：如果执行行数大于1，则覆盖redis数据
		//因为改变的是店铺菜单信息，所以对应的商品和目录都要进行覆盖，需要首先执行查询方法
		if (i>0){
			boolean b = myRedisService.coverShopMenuAndProductByShopIdInRedis(shopId, redisService);
			if (b!=true){
				System.out.println("redis覆盖canteen数据失败！");

			}

		}

		return i;

	}
	
	/**
     * 修改商品
     * 
     * @param shopProduct 商品信息
     * @return 结果
     */
	@Override
	public int updateProduct(ShopProduct shopProduct, MyRedisService myRedisService, RedisService redisService
	)
	{
		//新增商品的redis相关操作和修改一样，方法可复用
		System.out.println("这是新增商品方法的数据"+shopProduct);
		Long shopId = shopProduct.getShopId();
		shopId =1L;
		int i = shopProductMapper.updateProduct(shopProduct);
		//新增redis方法：如果执行行数大于1，则覆盖redis数据
		//因为改变的是店铺菜单信息，所以对应的商品和目录都要进行覆盖，需要首先执行查询方法
		if (i>0){
			boolean b = myRedisService.coverShopMenuAndProductByShopIdInRedis(shopId, redisService);
			if (b!=true){
				System.out.println("redis覆盖canteen数据失败！");

			}

		}

		return i;
	}

	/**
     * 删除商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductByIds(String ids,Long shopId, MyRedisService myRedisService, RedisService redisService)
	{
		//删除如果失败是否能说明redis宕机？不能，有可能是网络传输问题导致的删除失败
		//解决方式：
		//		1、再试一次，加延时器
		//	本次编写暂时不考虑超时情况，默认删除失败为redis宕机，不会造成redis引起的脏读

		Long delstatus = myRedisService.deleteShopMenuAndProductByShopIdInRedis(shopId,redisService);
		System.out.println("service层中删除目录方法查看redis中受影响的行数="+delstatus);
		return shopProductMapper.deleteProductByIds(Convert.toStrArray(ids));
	}
	
}
