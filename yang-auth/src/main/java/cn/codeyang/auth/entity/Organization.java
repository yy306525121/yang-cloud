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
@TableName("t_organization")
public class Organization extends BaseEntity<Organization> {
	private Long pid;
	private String name;
	private String description;
}
