package com.aaa.repast.admin.project.system.shopProduct.service;

import com.aaa.repast.admin.project.system.shopProduct.domain.ShopProduct;

import java.util.List;

/**
 * 商品 服务层
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
public interface IShopProductService
{
	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
	public ShopProduct selectProductById(Long id);
	
	/**
     * 查询商品列表
     * 
     * @param shopProduct 商品信息
     * @return 商品集合
     */
	public List<ShopProduct> selectProductList(ShopProduct shopProduct);
	
	/**
     * 新增商品
     * 
     * @param shopProduct 商品信息
     * @return 结果
     */
	public int insertProduct(ShopProduct shopProduct);
	
	/**
     * 修改商品
     * 
     * @param shopProduct 商品信息
     * @return 结果
     */
	public int updateProduct(ShopProduct shopProduct);
		
	/**
     * 删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProductByIds(String ids);
	
}
