package cn.codeyang.auth.entity;

import cn.codeyang.auth.entity.enums.BaseStatusEnum;
import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_system")
public class System extends BaseEntity<System> {

	private String icon;
	/**
	 * 系统名称
	 */
	private String name;

	/**
	 * 别名
	 */
	private String code;
	/**
	 * 系统标题
	 */
	private String title;
	/**
	 * 系统描述
	 */
	private String description;

	private String basePath;

	private BaseStatusEnum status;
}
