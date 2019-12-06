package com.aaa.repast.admin.project.system.productCategory.service;

import com.aaa.repast.admin.project.system.productCategory.domain.ProductCategory;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;

import java.util.List;

/**
 * 产品分类 服务层
 *
 * @author Regina
 * @date 2019-12-06
 */
public interface IProductCategoryService
{
	/**
	 * 查询产品分类信息
	 *
	 * @param id 产品分类ID
	 * @return 产品分类信息
	 */
	public ProductCategory selectProductCategoryById(Long id);

	/**
	 * 查询产品分类列表
	 *
	 * @param productCategory 产品分类信息
	 * @return 产品分类集合
	 */
	public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory);

	/**
	 * 新增产品分类
	 *
	 * @param productCategory 产品分类信息
	 * @return 结果
	 */
	public int insertProductCategory(ProductCategory productCategory,MyRedisService myRedisService, RedisService redisService);

	/**
	 * 修改产品分类
	 *
	 * @param productCategory 产品分类信息
	 * @return 结果
	 */
	public int updateProductCategory(ProductCategory productCategory,MyRedisService myRedisService, RedisService redisService);

	/**
	 * 删除产品分类信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteProductCategoryByIds(String ids,Long shopId,MyRedisService myRedisService, RedisService redisService);

}
