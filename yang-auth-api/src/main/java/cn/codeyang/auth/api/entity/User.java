package cn.codeyang.auth.api.entity;

import cn.codeyang.common.constant.Constants;
import cn.codeyang.common.domain.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "t_user")
public class User extends AbstractAuditingEntity implements UserDetails {

	private static final long serialVersionUID = -3923914335814749338L;


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	/**
	 * 用户名
	 */
	@NotNull
	@Pattern(regexp = Constants.USERNAME_REGEX)
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String username;
	/**
	 * 密码
	 */
	@JsonIgnore
	@NotNull
	@Size(min = 60, max = 60)
	@Column(length = 60, nullable = false)
	private String password;

	/**
	 * 昵称
	 */
	@Size(max = 50)
	@Column(length = 50)
	private String nickname;

	@Email
	@Size(min = 5, max = 254)
	@Column(unique = true)
	private String email;

	@Size(max = 256)
	@Column(length = 256)
	private String avatar;

	/**
	 * 是否激活
	 * 1:是
	 * 2:否
	 */
	@NotNull
	@Column(nullable = false)
	private Boolean activated;
	/**
	 * 用户语言
	 */
	@Size(min = 2, max = 6)
	@Column(name = "lang_key", length = 6)
	private String langKey;
	/**
	 * 激活码
	 */
	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	@JsonIgnore
	private String activationKey;
	/**
	 * 重置码
	 */
	@Size(max = 20)
	@Column(name = "reset_key", length = 20)
	@JsonIgnore
	private String resetKey;

	/**
	 * 重置时间
	 */
	@Column(name = "reset_date")
	private Instant resetDate = null;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "t_user_authority",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
	)
	@BatchSize(size = 20)
	private Set<Authority> authorities = new HashSet<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "t_user_role",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	)
	@BatchSize(size = 20)
	private Set<Role> roles = new HashSet<>();

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
