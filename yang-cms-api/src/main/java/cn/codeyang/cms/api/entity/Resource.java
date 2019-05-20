package cn.codeyang.cms.api.entity;

import cn.codeyang.auth.api.service.UserService;
import cn.codeyang.common.annotation.InjectField;
import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_resource")
public class Resource extends BaseEntity<Resource> {
	private String name;
	private String path;
	private String type;
	private String md5;
	private Long size;
	private Byte status;

	@InjectField(beanClass = UserService.class, param = "createUid", method = "getById", targetField = "username")
	@TableField(exist = false)
	private String createUserName;
}
