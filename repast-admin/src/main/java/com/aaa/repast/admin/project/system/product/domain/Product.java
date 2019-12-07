package com.aaa.repast.admin.project.system.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aaa.repast.admin.framework.web.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表 pms_product
 * 
 * @author Seven Lee
 * @date 2019-12-04
 */
public class Product extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;

	private MultipartFile picFile;
	/** 店铺ID */
	private Long shopId;
	/** 品牌ID */
	private Long brandId;
	/** 产品类别ID */
	private Long productCategoryId;
	/** 产品属性类别ID */
	private Long productAttributeCategoryId;
	/**  */
	private String name;
	/**  */
	private String pic;
	/** 货号 */
	private String productSn;
	/** 删除状态：0->未删除；1->已删除 */
	private Integer deleteStatus;
	/** 上架状态：0->下架；1->上架 */
	private Integer publishStatus;
	/** 新品状态:0->不是新品；1->新品 */
	private Integer newStatus;
	/** 推荐状态；0->不推荐；1->推荐 */
	private Integer recommandStatus;
	/** 排序 */
	private Integer sort;
	/** 销量 */
	private Integer sale;
	/**  */
	private BigDecimal price;
	/** 促销价格 */
	private BigDecimal promotionPrice;
	/** 赠送的积分 */
	private Integer giftPoint;
	/** 副标题 */
	private String subTitle;
	/** 商品描述 */
	private String description;
	/** 市场价 */
	private BigDecimal originalPrice;
	/** 库存 */
	private Integer stock;
	/** 库存预警值 */
	private Integer lowStock;
	/** 单位 */
	private String unit;
	/** 商品重量，默认为克 */
	private BigDecimal weight;
	/** 以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮 */
	private String serviceIds;
	/**  */
	private String keywords;
	/**  */
	private String note;
	/** 画册id */
	private Long albumId;
	/**  */
	private String detailTitle;
	/**  */
	private String detailDesc;
	/** 产品详情网页内容 */
	private String detailHtml;
	/** 移动端网页详情 */
	private String detailMobileHtml;
	/** 促销开始时间 */
	private Date promotionStartTime;
	/** 促销结束时间 */
	private Date promotionEndTime;
	/** 活动限购数量 */
	private Integer promotionPerLimit;
	/** 促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购 */
	private Integer promotionType;
	/** 品牌名称 */
	private String brandName;
	/** 商品分类名称 */
	private String productCategoryName;
	/** 币种，0-> 人民币;  1-> 积分 */
	private Integer currency;

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", picFile=" + picFile +
				", shopId=" + shopId +
				", brandId=" + brandId +
				", productCategoryId=" + productCategoryId +
				", productAttributeCategoryId=" + productAttributeCategoryId +
				", name='" + name + '\'' +
				", pic='" + pic + '\'' +
				", productSn='" + productSn + '\'' +
				", deleteStatus=" + deleteStatus +
				", publishStatus=" + publishStatus +
				", newStatus=" + newStatus +
				", recommandStatus=" + recommandStatus +
				", sort=" + sort +
				", sale=" + sale +
				", price=" + price +
				", promotionPrice=" + promotionPrice +
				", giftPoint=" + giftPoint +
				", subTitle='" + subTitle + '\'' +
				", description='" + description + '\'' +
				", originalPrice=" + originalPrice +
				", stock=" + stock +
				", lowStock=" + lowStock +
				", unit='" + unit + '\'' +
				", weight=" + weight +
				", serviceIds='" + serviceIds + '\'' +
				", keywords='" + keywords + '\'' +
				", note='" + note + '\'' +
				", albumId=" + albumId +
				", detailTitle='" + detailTitle + '\'' +
				", detailDesc='" + detailDesc + '\'' +
				", detailHtml='" + detailHtml + '\'' +
				", detailMobileHtml='" + detailMobileHtml + '\'' +
				", promotionStartTime=" + promotionStartTime +
				", promotionEndTime=" + promotionEndTime +
				", promotionPerLimit=" + promotionPerLimit +
				", promotionType=" + promotionType +
				", brandName='" + brandName + '\'' +
				", productCategoryName='" + productCategoryName + '\'' +
				", currency=" + currency +
				'}';
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MultipartFile getPicFile() {
		return picFile;
	}

	public void setPicFile(MultipartFile picFile) {
		this.picFile = picFile;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public Long getProductAttributeCategoryId() {
		return productAttributeCategoryId;
	}

	public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
		this.productAttributeCategoryId = productAttributeCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Integer getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(Integer newStatus) {
		this.newStatus = newStatus;
	}

	public Integer getRecommandStatus() {
		return recommandStatus;
	}

	public void setRecommandStatus(Integer recommandStatus) {
		this.recommandStatus = recommandStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Integer getGiftPoint() {
		return giftPoint;
	}

	public void setGiftPoint(Integer giftPoint) {
		this.giftPoint = giftPoint;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getLowStock() {
		return lowStock;
	}

	public void setLowStock(Integer lowStock) {
		this.lowStock = lowStock;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getServiceIds() {
		return serviceIds;
	}

	public void setServiceIds(String serviceIds) {
		this.serviceIds = serviceIds;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getDetailTitle() {
		return detailTitle;
	}

	public void setDetailTitle(String detailTitle) {
		this.detailTitle = detailTitle;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public String getDetailHtml() {
		return detailHtml;
	}

	public void setDetailHtml(String detailHtml) {
		this.detailHtml = detailHtml;
	}

	public String getDetailMobileHtml() {
		return detailMobileHtml;
	}

	public void setDetailMobileHtml(String detailMobileHtml) {
		this.detailMobileHtml = detailMobileHtml;
	}

	public Date getPromotionStartTime() {
		return promotionStartTime;
	}

	public void setPromotionStartTime(Date promotionStartTime) {
		this.promotionStartTime = promotionStartTime;
	}

	public Date getPromotionEndTime() {
		return promotionEndTime;
	}

	public void setPromotionEndTime(Date promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}

	public Integer getPromotionPerLimit() {
		return promotionPerLimit;
	}

	public void setPromotionPerLimit(Integer promotionPerLimit) {
		this.promotionPerLimit = promotionPerLimit;
	}

	public Integer getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(Integer promotionType) {
		this.promotionType = promotionType;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
}
