package cn.codeyang.auth.entity;

import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User extends BaseEntity<User> implements UserDetails {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickname;

	private String email;

	private String avatar;

	/**
	 * 是否激活
	 * 1:是
	 * 2:否
	 */
	private boolean activated;
	/**
	 * 用户语言
	 */
	private String langKey;
	/**
	 * 激活码
	 */
	private String activationKey;
	/**
	 * 重置码
	 */
	private String resetKey;

	/**
	 * 重置时间
	 */
	private Date resetTime;

	@TableField(exist = false)
	private Set<Authority> authorities = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
