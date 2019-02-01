package cn.codeyang.auth.entity;

import cn.codeyang.auth.entity.enums.PermissionStatus;
import cn.codeyang.auth.entity.enums.PermissionType;
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
@TableName("t_permission")
public class Authority extends BaseEntity<Authority>  implements GrantedAuthority {

	/*
	select
			<include refid="UpmsPermission_Column_List" />
		from upms_permission up where up.`status`=1 and up.permission_id in (
			select permission_id from upms_role_permission urp where urp.role_id in (
				select uur.role_id role_id from upms_user_role uur where uur.user_id=#{upmsUserId,jdbcType=INTEGER}
			)
			union
			select permission_id from upms_user_permission uup1 where uup1.user_id=#{upmsUserId,jdbcType=INTEGER} and uup1.type=1
		)
		and up.permission_id not in (
			select permission_id from upms_user_permission uup2 where uup2.user_id=#{upmsUserId,jdbcType=INTEGER} and uup2.type=-1
		) order by up.orders asc
	 */
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
