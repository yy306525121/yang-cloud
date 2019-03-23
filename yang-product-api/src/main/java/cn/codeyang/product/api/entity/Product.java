package cn.codeyang.product.api.entity;

import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_product")
public class Product extends BaseEntity<Product> {

	private Long id;
	private Long brandId;
	private Long categoryId;
	/**
	 * 货号
	 */
	private Integer sn;
	private String name;
	private String pic;
	/**
	 * 是否删除
	 * 1： 未删除
	 * -1：已删除
	 */
	private Integer deleteStatus;

	/**
	 * 上架状态
	 * -1： 下架
	 * 1： 上架
	 */
	private Integer publishStatus;

	/**
	 * 是否新品
	 * 1： 新品
	 * -1： 非新品
	 */
	private Integer newStatus;

	/**
	 * 是否推荐
	 * 1:  是
	 * -1： 否
	 */
	private Integer recommendStatus;

	/**
	 * 审核状态
	 * 0： 未审核
	 * 1：审核通过
	 * -1：审核未通过
	 */
	private Integer verifyStatus;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 销量
	 */
	private Integer sale;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 市场价
	 */
	private BigDecimal originalPrice;

	/**
	 * 促销价格
	 */
	private BigDecimal promotionPrice;

	/**
	 * 副标题
	 */
	private String subTitle;

	private String description;

	/**
	 * 库存
	 */
	private Integer stock;

	/**
	 * 库存预警值
	 */
	private Integer lowStock;
	/**
	 * 是否为预警商品
	 * -1： 否
	 * 1： 是
	 */
	private Integer previewStatus;

	/**
	 * 单位
	 */
	private String unit;

	private Double weight;

	private String keywords;

	private String note;
}
