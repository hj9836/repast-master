package com.aaa.repast.admin.project.system.shopInformation.controller;

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

import com.aaa.repast.admin.project.system.shopInformation.domain.ShopInformation;
import com.aaa.repast.admin.project.system.shopInformation.service.IShopInformationService;

import javax.servlet.http.HttpServletRequest;

/**
 * 店铺的基本 信息操作处理
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
@Controller
@RequestMapping("/system/shopInformation")
public class ShopInformationController extends BaseController
{
    private String prefix = "system/shopInformation";
	
	@Autowired
	private IShopInformationService shopInformationService;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private MyRedisService myRedisService;

	@Autowired
	private RedisService redisService;
	
	@RequiresPermissions("system:shopInformation:view")
	@GetMapping()
	public String shopInformation()
	{
	    return prefix + "/shopInformation";
	}
	
	/**
	 * 查询店铺的基本列表
	 */
	@RequiresPermissions("system:shopInformation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ShopInformation shopInformation)
	{
		startPage();
        List<ShopInformation> list = shopInformationService.selectShopInformationList(shopInformation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出店铺的基本列表
	 */
	@RequiresPermissions("system:shopInformation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShopInformation shopInformation)
    {
    	List<ShopInformation> list = shopInformationService.selectShopInformationList(shopInformation);
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
	@RequiresPermissions("system:shopInformation:add")
	@Log(title = "店铺的基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ShopInformation shopInformation)
	{		
		return toAjax(shopInformationService.insertShopInformation(shopInformation,myRedisService,redisService));
	}

	/**
	 * 修改店铺的基本
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ShopInformation shopInformation = shopInformationService.selectShopInformationById(id);
		mmap.put("shopInformation", shopInformation);
	    return prefix + "/edit";
	}

	/**
	 * 修改店铺的图片
	 */
	@GetMapping("/editPic/{id}")
	public String editPic(@PathVariable("id") Long id, ModelMap mmap)
	{
		ShopInformation shopInformation = shopInformationService.selectShopInformationById(id);
		mmap.put("shopInformation", shopInformation);
		return prefix + "/editPic";
	}
	
	/**
	 * 修改保存店铺的基本
	 */
	@RequiresPermissions("system:shopInformation:edit")
	@Log(title = "店铺的基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ShopInformation shopInformation)
	{		
		return toAjax(shopInformationService.updateShopInformation(shopInformation,myRedisService,redisService));
	}

	/**
	 * 修改保存店铺的图片
	 */
	@RequiresPermissions("system:shopInformation:editPic")
	@Log(title = "店铺的基本", businessType = BusinessType.UPDATE)
	@PostMapping("/editPic")
	@ResponseBody
	public AjaxResult editPicSave(ShopInformation shopInformation, HttpServletRequest request)
	{
		System.out.println("查看+量化考核图片+源文件名"+shopInformation.getAssessImg());
		System.out.println("查看+卫生许可+源文件名"+shopInformation.getSanitationLicenseImg().getOriginalFilename());
		System.out.println("查看+食品安全许可证+源文件名"+shopInformation.getFoodLicenseImg().getOriginalFilename());
		System.out.println("查看+营业执照+源文件名"+shopInformation.getBusinessLicenseImg().getOriginalFilename());
		System.out.println("查看+商家实景+源文件名"+shopInformation.getImagesImg().getOriginalFilename());

		Long shopId = shopInformation.getId();
		System.out.println("查看需要修改的店铺的shopId："+shopId);
		uploadService.uploadShopPicAjax(shopInformation,request,shopId);

		//TODO 老杨别忘了加代码
		return null;
	}
	
	/**
	 * 删除店铺的基本
	 */
	@RequiresPermissions("system:shopInformation:remove")
	@Log(title = "店铺的基本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{	Long shopId =1l;
		return toAjax(shopInformationService.deleteShopInformationByIds(ids,shopId,myRedisService,redisService));
	}
	
}
