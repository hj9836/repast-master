package com.aaa.repast.admin.project.system.productCategory.mapper;

import com.aaa.repast.admin.project.system.productCategory.domain.ProductCategory;
import com.aaa.repast.admin.project.tool.redisTools.domain.CanTeenDateVo;
import com.aaa.repast.admin.project.tool.redisTools.domain.ShopInfoCutom;

import java.util.List;
import java.util.Map;

/**
 * 产品分类 数据层
 *
 * @author Regina
 * @date 2019-12-06
 */
public interface ProductCategoryMapper
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
	public int insertProductCategory(ProductCategory productCategory);

	/**
	 * 修改产品分类
	 *
	 * @param productCategory 产品分类信息
	 * @return 结果
	 */
	public int updateProductCategory(ProductCategory productCategory);

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

	//————————————————————————————————————laoyang——————————————————————————————————————————————

	List<CanTeenDateVo> getCanteenDateByShopId(Long shopId);


	ShopInfoCutom getShopAllInfoLAOYANG(Long shopId);

	Integer updateShopImagesById(Map<String,String> map);

	Integer updateShopBusinessLicenseById(Map<String,String> map);

	Integer updateShopFoodLicensecById(Map<String,String> map);

	Integer updateShopSanitationLicenseById(Map<String,String> map);

	Integer updateShopAssessById(Map<String,String> map);

















	//————————————————————————————————————laoyang——————————————————————————————————————————————

}