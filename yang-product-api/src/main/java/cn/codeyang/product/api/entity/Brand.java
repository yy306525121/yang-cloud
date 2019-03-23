package cn.codeyang.product.api.entity;

import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_brand")
public class Brand extends BaseEntity<Brand> {
	private Long id;
	private String name;
	private Integer sort;
	private String logo;
	private String bigPic;

}
