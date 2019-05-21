package cn.codeyang.auth.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "t_authority")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 63940729772238363L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 权限名称
	 */
	@NotNull
	@Size(max = 50)
	@Column(length = 50)
	private String name;

	/**
	 * 类型
	 * 1：目录
	 * 2：菜单
	 * 3：按钮
	 */
	@NotNull
	@Column
	private Integer type;

	/**
	 * 权限值
	 */
	@NotNull
	@Size(max = 50)
	@Column(name = "authority_value", length = 50)
	private String authorityValue;

	@NotNull
	@Size(max = 256)
	@Column(length = 256)
	private String uri;
	@Size(max = 50)
	@Column(length = 50)
	private String icon;

	/**
	 * 状态
	 * 1：正常
	 * 2：禁止
	 */
	@NotNull
	@Column
	private Integer status;


	@Override
	public String getAuthority() {
		return authorityValue;
	}
}
