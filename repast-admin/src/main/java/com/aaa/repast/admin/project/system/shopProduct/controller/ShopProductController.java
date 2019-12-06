package com.aaa.repast.admin.project.system.shopProduct.controller;

import com.aaa.repast.admin.framework.aspectj.lang.annotation.Log;
import com.aaa.repast.admin.framework.aspectj.lang.enums.BusinessType;
import com.aaa.repast.admin.framework.poi.ExcelUtil;
import com.aaa.repast.admin.framework.web.controller.BaseController;
import com.aaa.repast.admin.framework.web.domain.AjaxResult;
import com.aaa.repast.admin.framework.web.page.TableDataInfo;
import com.aaa.repast.admin.project.system.shopProduct.domain.ShopProduct;
import com.aaa.repast.admin.project.system.shopProduct.service.IShopProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品 信息操作处理
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Controller
@RequestMapping("/system/shopProduct")
public class ShopProductController extends BaseController
{
    private String prefix = "system/shopProduct";
	
	@Autowired
	private IShopProductService productService;
	
	@RequiresPermissions("system:shopProduct:view")
	@GetMapping()
	public String product()
	{
	    return prefix + "/shopProduct";
	}
	
	/**
	 * 查询商品列表
	 */
	@RequiresPermissions("system:shopProduct:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ShopProduct shopProduct)
	{
		startPage();
        List<ShopProduct> list = productService.selectProductList(shopProduct);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品列表
	 */
	@RequiresPermissions("system:shopProduct:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShopProduct shopProduct)
    {
    	List<ShopProduct> list = productService.selectProductList(shopProduct);
        ExcelUtil<ShopProduct> util = new ExcelUtil<ShopProduct>(ShopProduct.class);
        return util.exportExcel(list, "shopProduct");
    }
	
	/**
	 * 新增商品
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品
	 */
	@RequiresPermissions("system:shopProduct:add")
	@Log(title = "商品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ShopProduct shopProduct)
	{		
		return toAjax(productService.insertProduct(shopProduct));
	}

	/**
	 * 修改商品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ShopProduct shopProduct = productService.selectProductById(id);
		mmap.put("shopProduct", shopProduct);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品
	 */
	@RequiresPermissions("system:shopProduct:edit")
	@Log(title = "商品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ShopProduct shopProduct)
	{		
		return toAjax(productService.updateProduct(shopProduct));
	}
	
	/**
	 * 删除商品
	 */
	@RequiresPermissions("system:shopProduct:remove")
	@Log(title = "商品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(productService.deleteProductByIds(ids));
	}
	
}
