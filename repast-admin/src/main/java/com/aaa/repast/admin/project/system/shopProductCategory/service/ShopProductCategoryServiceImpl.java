package com.aaa.repast.admin.project.system.shopProductCategory.service;

import com.aaa.repast.admin.project.system.shopProductCategory.domain.ShopProductCategory;
import com.aaa.repast.admin.project.system.shopProductCategory.mapper.ShopProductCategoryMapper;
import com.aaa.repast.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品分类 服务层实现
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Service
public class ShopProductCategoryServiceImpl implements IShopProductCategoryService
{
	@Autowired
	private ShopProductCategoryMapper shopProductCategoryMapper;

	/**
     * 查询产品分类信息
     * 
     * @param id 产品分类ID
     * @return 产品分类信息
     */
    @Override
	public ShopProductCategory selectProductCategoryById(Long id)
	{
	    return shopProductCategoryMapper.selectProductCategoryById(id);
	}
	
	/**
     * 查询产品分类列表
     * 
     * @param productCategory 产品分类信息
     * @return 产品分类集合
     */
	@Override
	public List<ShopProductCategory> selectProductCategoryList(ShopProductCategory shopProductCategory)
	{
	    return shopProductCategoryMapper.selectProductCategoryList(shopProductCategory);
	}
	
    /**
     * 新增产品分类
     * 
     * @param productCategory 产品分类信息
     * @return 结果
     */
	@Override
	public int insertProductCategory(ShopProductCategory shopProductCategory)
	{
	    return shopProductCategoryMapper.insertProductCategory(shopProductCategory);
	}
	
	/**
     * 修改产品分类
     * 
     * @param productCategory 产品分类信息
     * @return 结果
     */
	@Override
	public int updateProductCategory(ShopProductCategory shopProductCategory)
	{
	    return shopProductCategoryMapper.updateProductCategory(shopProductCategory);
	}

	/**
     * 删除产品分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductCategoryByIds(String ids)
	{
		int i = 0;
		i = shopProductCategoryMapper.deleteProductCategoryByIds(Convert.toStrArray(ids));
		if (i==1){
			List<Long> productIds = shopProductCategoryMapper.selectProductIdByCategoryId(ids);
			int i1 = shopProductCategoryMapper.deleteProductListById(productIds);

		}
		return i;
	}

	@Override
	public List<ShopProductCategory> selectAllCategory() {
		return shopProductCategoryMapper.selectAllCategory();
	}


}
