package cn.codeyang.auth.entity;

import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_role")
public class Role extends BaseEntity<Role> {
	private String name;
	private String title;
	private String description;
}
