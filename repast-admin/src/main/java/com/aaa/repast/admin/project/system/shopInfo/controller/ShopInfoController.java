package com.aaa.repast.admin.project.system.shopInfo.controller;

import com.aaa.repast.admin.framework.aspectj.lang.annotation.Log;
import com.aaa.repast.admin.framework.aspectj.lang.enums.BusinessType;
import com.aaa.repast.admin.framework.poi.ExcelUtil;
import com.aaa.repast.admin.framework.web.controller.BaseController;
import com.aaa.repast.admin.framework.web.domain.AjaxResult;
import com.aaa.repast.admin.framework.web.page.TableDataInfo;
import com.aaa.repast.admin.project.system.shopInfo.service.IShopInfoService;
import com.aaa.repast.admin.project.system.shopInformation.domain.ShopInformation;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 店铺的基本 信息操作处理
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Controller
@RequestMapping("/system/shopInfo")
public class ShopInfoController extends BaseController
{
    private String prefix = "system/shopInfo";
	
	@Autowired
	private IShopInfoService shopInformationService;

	@Autowired
	private MyRedisService myRedisService;

	@Autowired
	private RedisService redisService;
	
	@RequiresPermissions("system:shopInfo:view")
	@GetMapping()
	public String shopInformation()
	{
	    return prefix + "/shopInfo";
	}
	
	/**
	 * 查询店铺的基本列表
	 */
	@RequiresPermissions("system:shopInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ShopInformation shopInformation)
	{
		startPage();
        List<ShopInformation> list = shopInformationService.selectShopInfoList(shopInformation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出店铺的基本列表
	 */
	@RequiresPermissions("system:shopInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShopInformation shopInformation)
    {
    	List<ShopInformation> list = shopInformationService.selectShopInfoList(shopInformation);
        ExcelUtil<ShopInformation> util = new ExcelUtil<ShopInformation>(ShopInformation.class);
        return util.exportExcel(list, "shopInformation");
    }
	
	/**
	 * 新增店铺的基本
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存店铺的基本
	 */
	@RequiresPermissions("system:shopInfo:add")
	@Log(title = "店铺的基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ShopInformation shopInformation)
	{
		System.out.println("添加店铺被调用");
		return toAjax(shopInformationService.insertShopInfo(shopInformation,myRedisService,redisService));

	}

	/**
	 * 修改店铺的基本
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ShopInformation shopInformation = shopInformationService.selectShopInfoById(id);
		mmap.put("shopInformation", shopInformation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存店铺的基本
	 */
	@RequiresPermissions("system:shopInfo:edit")
	@Log(title = "店铺的基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ShopInformation shopInformation)
	{		
		return toAjax(shopInformationService.updateShopInfo(shopInformation,myRedisService,redisService));
	}
	
	/**
	 * 删除店铺的基本
	 */
	@RequiresPermissions("system:shopInfo:remove")
	@Log(title = "店铺的基本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		Long shopId =1l;
		return toAjax(shopInformationService.deleteShopInfoByIds(ids,shopId,myRedisService,redisService));
	}
	
}
