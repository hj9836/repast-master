package com.aaa.repast.admin.project.system.shopProductCategory.mapper;

import com.aaa.repast.admin.project.system.shopProductCategory.domain.ShopProductCategory;

import java.util.List;

/**
 * 产品分类 数据层
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
public interface ShopProductCategoryMapper
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
	public int insertProductCategory(ShopProductCategory shopProductCategory);
	
	/**
     * 修改产品分类
     * 
     * @param ShopProductCategory 产品分类信息
     * @return 结果
     */
	public int updateProductCategory(ShopProductCategory shopProductCategory);
	
	/**
     * 删除产品分类
     * 
     * @param id 产品分类ID
     * @return 结果
     */
	public int deleteProductCategoryById(Long id);
	
	/**
     * 批量删除产品分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductCategoryByIds(String[] ids);

	/**
	 * 根据店铺ID查询
	 * @return
	 */
	public List<ShopProductCategory> selectAllCategory();

	public List<Long> selectProductIdByCategoryId(String a);

	public int deleteProductListById(List<Long> list);

}