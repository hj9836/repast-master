package com.aaa.repast.admin.project.system.product.controller;

import java.util.List;
import com.aaa.repast.admin.framework.aspectj.lang.annotation.Log;
import com.aaa.repast.admin.framework.aspectj.lang.enums.BusinessType;
import com.aaa.repast.admin.framework.poi.ExcelUtil;
import com.aaa.repast.admin.framework.web.controller.BaseController;
import com.aaa.repast.admin.framework.web.domain.AjaxResult;
import com.aaa.repast.admin.framework.web.page.TableDataInfo;
import com.aaa.repast.admin.ftp.service.UploadService;
import com.aaa.repast.admin.project.tool.redisTools.service.MyRedisService;
import com.aaa.repast.admin.redis.service.RedisService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aaa.repast.admin.project.system.product.domain.Product;
import com.aaa.repast.admin.project.system.product.service.IProductService;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品 信息操作处理
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Controller
@RequestMapping("/system/product")
public class ProductController extends BaseController
{
    private String prefix = "system/product";
	
	@Autowired
	private IProductService productService;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private MyRedisService myRedisService;


	@Autowired
	private RedisService redisService;
	@RequiresPermissions("system:product:view")
	@GetMapping()
	public String product()
	{
	    return prefix + "/product";
	}
	
	/**
	 * 查询商品列表
	 */
	@RequiresPermissions("system:product:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Product product)
	{
		startPage();
        List<Product> list = productService.selectProductList(product);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品列表
	 */
	@RequiresPermissions("system:product:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Product product)
    {
    	List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product.class);
        return util.exportExcel(list, "product");
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
	@RequiresPermissions("system:product:add")
	@Log(title = "商品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Product product)
	{

		return toAjax(productService.insertProduct(product,myRedisService,redisService));
	}

	/**
	 * 修改商品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Product product = productService.selectProductById(id);
		mmap.put("product", product);
	    return prefix + "/edit";
	}

	/**
	 * 修改商品图片按钮页面信息
	 */
	@GetMapping("/editPic/{id}")
	public String editPic(@PathVariable("id") Long id, ModelMap mmap)
	{
		Product product = productService.selectProductById(id);
		mmap.put("product", product);
		return prefix + "/editPic";
	}

	/**
	 * 修改保存商品
	 */
	@RequiresPermissions("system:product:edit")
	@Log(title = "商品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Product product)
	{		
		return toAjax(productService.updateProduct(product,myRedisService,redisService));
	}

	/**
	 * 修改保存商品图片
	 */
	@RequiresPermissions("system:product:editPic")
	@Log(title = "商品图片", businessType = BusinessType.UPDATE)
	@PostMapping("/editPic")
	@ResponseBody
	public AjaxResult editPicSave(Product product, HttpServletRequest request) {

		System.out.println("查看文件原名称："+product.getPicFile().getOriginalFilename());
		Long productId = product.getId();
		System.out.println("查看需要修改的商品的shopId："+productId);
		uploadService.uploadProductPicAjax(product.getPicFile(),request,productId);
		return AjaxResult.success();
	}
	/**
	 * 删除商品
	 */
	@RequiresPermissions("system:product:remove")
	@Log(title = "商品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		Long shopId =1L;
		return toAjax(productService.deleteProductByIds(ids,shopId,myRedisService,redisService));
	}
	
}
