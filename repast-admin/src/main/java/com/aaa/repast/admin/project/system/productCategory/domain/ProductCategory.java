package com.aaa.repast.admin.project.system.productCategory.domain;

import com.aaa.repast.admin.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品分类表 pms_product_category
 *
 * @author Regina
 * @date 2019-12-05
 */
public class ProductCategory extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/**  */
	private Long id;
	/**  */
	private Long shopId;
	/** 上级分类的编号：0表示一级分类 */
	private Long parentId;
	/**  */
	private String name;
	/** 分类级别：0->1级；1->2级 */
	private Integer level;
	/** 显示状态：0->不显示；1->显示 */
	private Integer showStatus;
	/**  */
	private Integer sort;
	/** 图标 */
	private String icon;
	/**  */
	private String keywords;
	/** 描述 */
	private String description;

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}
	public void setShopId(Long shopId)
	{
		this.shopId = shopId;
	}

	public Long getShopId()
	{
		return shopId;
	}
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}

	public Long getParentId()
	{
		return parentId;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	public void setLevel(Integer level)
	{
		this.level = level;
	}

	public Integer getLevel()
	{
		return level;
	}
	public void setShowStatus(Integer showStatus)
	{
		this.showStatus = showStatus;
	}

	public Integer getShowStatus()
	{
		return showStatus;
	}
	public void setSort(Integer sort)
	{
		this.sort = sort;
	}

	public Integer getSort()
	{
		return sort;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public String getIcon()
	{
		return icon;
	}
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public String getKeywords()
	{
		return keywords;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("shopId", getShopId())
				.append("parentId", getParentId())
				.append("name", getName())
				.append("level", getLevel())
				.append("showStatus", getShowStatus())
				.append("sort", getSort())
				.append("icon", getIcon())
				.append("keywords", getKeywords())
				.append("description", getDescription())
				.toString();
	}
}
