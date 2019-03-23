package cn.codeyang.product.api.entity;

import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_category")
public class Category extends BaseEntity<Category> {
	private Long id;
	private Long pid;
	private String name;
	private String icon;
	private String keyword;
	private String description;
	private Integer sort;
}
