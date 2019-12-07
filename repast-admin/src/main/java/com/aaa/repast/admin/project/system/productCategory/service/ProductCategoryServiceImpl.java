package com.aaa.repast.admin.project.system.productCategory.service;

import com.aaa.repast.admin.project.system.productCategory.domain.ProductCategory;
import com.aaa.repast.admin.project.system.productCategory.mapper.ProductCategoryMapper;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;
import com.aaa.repast.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 产品分类 服务层实现
 *
 * @author Regina
 * @date 2019-12-06
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService
{
	@Autowired
	private ProductCategoryMapper productCategoryMapper;



	/**
	 * 查询产品分类信息
	 *
	 * @param id 产品分类ID
	 * @return 产品分类信息
	 */
	@Override
	public ProductCategory selectProductCategoryById(Long id)
	{
		return productCategoryMapper.selectProductCategoryById(id);
	}

	/**
	 * 查询产品分类列表
	 *
	 * @param productCategory 产品分类信息
	 * @return 产品分类集合
	 */
	@Override
	public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory)
	{
		return productCategoryMapper.selectProductCategoryList(productCategory);
	}

	/**
	 * 新增产品分类
	 *
	 * @param productCategory 产品分类信息
	 * @return 结果
	 */
	@Override
	public int insertProductCategory(ProductCategory productCategory,MyRedisService myRedisService, RedisService redisService)
	{
		//新增商品的redis相关操作和修改一样，方法可复用
		System.out.println("这是新增菜单方法的数据"+productCategory);
		Long shopId = productCategory.getShopId();
		shopId =1l;
		int i = productCategoryMapper.insertProductCategory(productCategory);
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
	 * 修改产品分类
	 *
	 * @param productCategory 产品分类信息
	 * @return 结果
	 */
	@Override
	public int updateProductCategory(ProductCategory productCategory, MyRedisService myRedisService, RedisService redisService) {
		System.out.println("这是修改菜单方法的被修改数据"+productCategory);
		Long shopId = productCategory.getShopId();
		shopId =1L;
		int i = productCategoryMapper.updateProductCategory(productCategory);
		//新增redis方法：如果执行行数大于0，则覆盖redis数据
		//因为改变的是店铺菜单信息，所以对应的商品和目录都要进行覆盖，需要首先执行查询方法
		if (i>0){
			boolean b = myRedisService.coverShopMenuAndProductByShopIdInRedis(shopId,redisService);
			if (b!=true){
				System.out.println("redis覆盖canteen数据失败！");

			}

		}

		return i;
	}

	/**
	 * 删除产品分类对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteProductCategoryByIds(String ids,Long shopId,MyRedisService myRedisService, RedisService redisService)
	{
		//删除如果失败是否能说明redis宕机？不能，有可能是网络传输问题导致的删除失败
		//解决方式：
		//		1、再试一次，加延时器
		//	本次编写暂时不考虑超时情况，默认删除失败为redis宕机，不会造成redis引起的脏读

		Long delstatus = myRedisService.deleteShopMenuAndProductByShopIdInRedis(shopId, redisService);
		System.out.println("service层中删除目录方法查看redis中受影响的行数="+delstatus);


		return productCategoryMapper.deleteProductCategoryByIds(Convert.toStrArray(ids));
	}

}
