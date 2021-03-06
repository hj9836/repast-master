package com.aaa.repast.admin.project.system.shopProductCategory.service;

import com.aaa.repast.admin.project.system.shopProductCategory.domain.ShopProductCategory;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;

import java.util.List;

/**
 * 产品分类 服务层
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
public interface IShopProductCategoryService
{
	/**
     * 查询产品分类信息
     * 
     * @param id 产品分类ID
     * @return 产品分类信息
     */
	public ShopProductCategory selectProductCategoryById(Long id);
	
	/**
     * 查询产品分类列表
     * 
     * @param ShopProductCategory 产品分类信息
     * @return 产品分类集合
     */
	public List<ShopProductCategory> selectProductCategoryList(ShopProductCategory shopProductCategory);
	
	/**
     * 新增产品分类
     * 
     * @param ShopProductCategory 产品分类信息
     * @return 结果
     */
	public int insertProductCategory(ShopProductCategory shopProductCategory, MyRedisService myRedisService, RedisService redisService);
	
	/**
     * 修改产品分类
     * 
     * @param ShopProductCategory 产品分类信息
     * @return 结果
     */
	public int updateProductCategory(ShopProductCategory shopProductCategory, MyRedisService myRedisService, RedisService redisService);
		
	/**
     * 删除产品分类信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductCategoryByIds(String ids,Long shopId, MyRedisService myRedisService, RedisService redisService);

	public List<ShopProductCategory> selectAllCategory();
	
}
