package com.aaa.repast.admin.project.system.shopProductCategory.controller;

import com.aaa.repast.admin.framework.aspectj.lang.annotation.Log;
import com.aaa.repast.admin.framework.aspectj.lang.enums.BusinessType;
import com.aaa.repast.admin.framework.poi.ExcelUtil;
import com.aaa.repast.admin.framework.web.controller.BaseController;
import com.aaa.repast.admin.framework.web.domain.AjaxResult;
import com.aaa.repast.admin.framework.web.page.TableDataInfo;
import com.aaa.repast.admin.project.system.shopProductCategory.domain.ShopProductCategory;
import com.aaa.repast.admin.project.system.shopProductCategory.service.IShopProductCategoryService;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 产品分类 信息操作处理
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Controller
@RequestMapping("/system/shopProductCategory")
public class ShopProductCategoryController extends BaseController
{
    private String prefix = "system/shopProductCategory";
	
	@Autowired
	private IShopProductCategoryService shopProductCategoryService;

	@Autowired
	private MyRedisService myRedisService;


	@Autowired
	private RedisService redisService;
	
	@RequiresPermissions("system:shopProductCategory:view")
	@GetMapping()
	public String shopProductCategory()
	{
	    return prefix + "/shopProductCategory";
	}
	
	/**
	 * 查询产品分类列表
	 */
	@RequiresPermissions("system:shopProductCategory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list()
	{
		startPage();
        List<ShopProductCategory> list = shopProductCategoryService.selectAllCategory();
		return getDataTable(list);
	}
	
	
	/**
	 * 导出产品分类列表
	 */
	@RequiresPermissions("system:shopProductCategory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShopProductCategory shopProductCategory)
    {
    	List<ShopProductCategory> list = shopProductCategoryService.selectProductCategoryList(shopProductCategory);
        ExcelUtil<ShopProductCategory> util = new ExcelUtil<ShopProductCategory>(ShopProductCategory.class);
        return util.exportExcel(list, "shopProductCategory");
    }
	
	/**
	 * 新增产品分类
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存产品分类
	 */
	@RequiresPermissions("system:shopProductCategory:add")
	@Log(title = "产品分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ShopProductCategory shopProductCategory)
	{		
		return toAjax(shopProductCategoryService.insertProductCategory(shopProductCategory,myRedisService,redisService));
	}

	/**
	 * 修改产品分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ShopProductCategory shopProductCategory = shopProductCategoryService.selectProductCategoryById(id);
		mmap.put("shopProductCategory", shopProductCategory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存产品分类
	 */
	@RequiresPermissions("system:shopProductCategory:edit")
	@Log(title = "产品分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ShopProductCategory ShopProductCategory)
	{		
		return toAjax(shopProductCategoryService.updateProductCategory(ShopProductCategory,myRedisService,redisService));
	}
	
	/**
	 * 删除产品分类
	 */
	@RequiresPermissions("system:shopProductCategory:remove")
	@Log(title = "产品分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		Long shopId = 1l;
		return toAjax(shopProductCategoryService.deleteProductCategoryByIds(ids,shopId,myRedisService,redisService));
	}
	
}
