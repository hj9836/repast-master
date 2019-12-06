package com.aaa.repast.admin.project.system.shopProduct.service;

import com.aaa.repast.admin.project.system.shopProduct.domain.ShopProduct;
import com.aaa.repast.admin.project.system.shopProduct.mapper.ShopProductMapper;
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
	public int insertProduct(ShopProduct shopProduct)
	{
	    return shopProductMapper.insertProduct(shopProduct);
	}
	
	/**
     * 修改商品
     * 
     * @param shopProduct 商品信息
     * @return 结果
     */
	@Override
	public int updateProduct(ShopProduct shopProduct)
	{
	    return shopProductMapper.updateProduct(shopProduct);
	}

	/**
     * 删除商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductByIds(String ids)
	{
		return shopProductMapper.deleteProductByIds(Convert.toStrArray(ids));
	}
	
}
