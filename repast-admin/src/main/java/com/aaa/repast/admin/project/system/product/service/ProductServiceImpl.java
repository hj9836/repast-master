package com.aaa.repast.admin.project.system.product.service;

import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;
import com.aaa.repast.common.support.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.aaa.repast.admin.project.system.product.mapper.ProductMapper;
import com.aaa.repast.admin.project.system.product.domain.Product;

/**
 * 商品 服务层实现
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Service
public class ProductServiceImpl implements IProductService
{
	@Autowired
	private ProductMapper productMapper;

	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
    @Override
	public Product selectProductById(Long id)
	{
	    return productMapper.selectProductById(id);
	}
	
	/**
     * 查询商品列表
     * 
     * @param product 商品信息
     * @return 商品集合
     */
	@Override
	public List<Product> selectProductList(Product product)
	{
	    return productMapper.selectProductList(product);
	}
	
    /**
     * 新增商品
     * 
     * @param product 商品信息
     * @return 结果
     */
	@Override
	public int insertProduct(Product product,MyRedisService myRedisService, RedisService redisService)
	{
		//新增商品的redis相关操作和修改一样，方法可复用
		System.out.println("这是新增商品方法的数据"+product);
		Long shopId = product.getShopId();
		shopId =1l;
		int i = productMapper.insertProduct(product);
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
     * 修改商品
     * 
     * @param product 商品信息
     * @return 结果
     */
	@Override
	public int updateProduct(Product product,MyRedisService myRedisService, RedisService redisService)
	{
		//新增商品的redis相关操作和修改一样，方法可复用
		System.out.println("这是修改商品方法的数据"+product);
		Long shopId = product.getShopId();
		shopId =1l;
		int i = productMapper.updateProduct(product);
		//新增redis方法：如果执行行数大于1，则覆盖redis数据
		//因为改变的是店铺菜单信息，所以对应的商品和目录都要进行覆盖，需要首先执行查询方法
		if (i>0){
			boolean b = myRedisService.coverShopMenuAndProductByShopIdInRedis(shopId, redisService);
			if (b!=true){
				System.out.println("redis覆盖canteen数据失败");

			}

		}

		return i;
	}

	/**
     * 删除商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteProductByIds(String ids,Long shopId,MyRedisService myRedisService, RedisService redisService)
	{
		//删除如果失败是否能说明redis宕机？不能，有可能是网络传输问题导致的删除失败
		//解决方式：
		//		1、再试一次，加延时器
		//	本次编写暂时不考虑超时情况，默认删除失败为redis宕机，不会造成redis引起的脏读

		Long delstatus = myRedisService.deleteShopMenuAndProductByShopIdInRedis(shopId,redisService);
		System.out.println("service层中删除目录方法查看redis中受影响的行数="+delstatus);
		return productMapper.deleteProductByIds(Convert.toStrArray(ids));
	}
	
}
