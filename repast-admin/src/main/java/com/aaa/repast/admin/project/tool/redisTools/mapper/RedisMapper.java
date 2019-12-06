package com.aaa.repast.admin.project.tool.redisTools.mapper;


import com.aaa.repast.admin.project.tool.redisTools.domain.CanTeenDateVo;

import java.util.List;

/**
 * 产品分类 数据层
 *
 * @author Regina
 * @date 2019-12-06
 */
public interface RedisMapper
{

	List<CanTeenDateVo> getCanteenDateByShopId(Long shopId);

}