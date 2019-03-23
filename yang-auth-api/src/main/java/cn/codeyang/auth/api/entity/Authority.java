package cn.codeyang.auth.api.entity;

import cn.codeyang.auth.api.entity.enums.PermissionStatus;
import cn.codeyang.auth.api.entity.enums.PermissionType;
import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_authority")
public class Authority extends BaseEntity<Authority> implements GrantedAuthority {
	private Long systemId;
	private Long pid;

	private String name;
	private PermissionType type;
	private String authorityValue;
	private String uri;
	private String icon;
	private PermissionStatus status;


	@Override
	public String getAuthority() {
		return authorityValue;
	}
}
