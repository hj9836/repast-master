package com.aaa.repast.admin.project.system.productCategory.service;

import com.aaa.repast.admin.project.system.productCategory.domain.ProductCategory;
import com.aaa.repast.admin.project.system.productCategory.mapper.ProductCategoryMapper;
import com.aaa.repast.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public int insertProductCategory(ProductCategory productCategory)
	{
		return productCategoryMapper.insertProductCategory(productCategory);
	}

	/**
	 * 修改产品分类
	 *
	 * @param productCategory 产品分类信息
	 * @return 结果
	 */
	@Override
	public int updateProductCategory(ProductCategory productCategory)
	{
		return productCategoryMapper.updateProductCategory(productCategory);
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
		return productCategoryMapper.deleteProductCategoryByIds(Convert.toStrArray(ids));
	}

}
