package com.aaa.repast.admin.project.system.shopInformation.mapper;

import com.aaa.repast.admin.project.system.shopInformation.domain.ShopInformation;
import java.util.List;	

/**
 * 店铺的基本 数据层
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
public interface ShopInformationMapper 
{
	/**
     * 查询店铺的基本信息
     * 
     * @param id 店铺的基本ID
     * @return 店铺的基本信息
     */
	ShopInformation selectShopInformationById(Long id);
	
	/**
     * 查询店铺的基本列表
     * 
     * @param shopInformation 店铺的基本信息
     * @return 店铺的基本集合
     */
	List<ShopInformation> selectShopInformationList(ShopInformation shopInformation);
	
	/**
     * 新增店铺的基本
     * 
     * @param shopInformation 店铺的基本信息
     * @return 结果
     */
	int insertShopInformation(ShopInformation shopInformation);
	
	/**
     * 修改店铺的基本
     * 
     * @param shopInformation 店铺的基本信息
     * @return 结果
     */
	int updateShopInformation(ShopInformation shopInformation);
	
	/**
     * 删除店铺的基本
     * 
     * @param id 店铺的基本ID
     * @return 结果
     */
	int deleteShopInformationById(Long id);
	
	/**
     * 批量删除店铺的基本
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteShopInformationByIds(String[] ids);
	
}