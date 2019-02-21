package cn.codeyang.cms.entity;

import cn.codeyang.common.entity.base.BaseEntity;
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
}
