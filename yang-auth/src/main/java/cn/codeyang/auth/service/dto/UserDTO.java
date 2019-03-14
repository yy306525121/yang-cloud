package cn.codeyang.auth.service.dto;

import cn.codeyang.auth.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

/**
 * @author yangzhongyang
 */
@Data
public class UserDTO {
	private Long id;
	@NotBlank
	@Size(min = 1, max = 50)
	private String username;

	@Email
	@Size(min = 5, max = 254)
	@NotBlank
	private String email;

	private boolean activated = false;

	@Size(min = 2, max = 6)
	private String langKey;

	@Size(max = 256)
	private String avatar;

	private Collection<? extends GrantedAuthority> authorities;

	private Long createUid;
	private Date createTime;
	private Long updateUid;
	private Date updateTime;

	public UserDTO() {
		// Empty constructor needed for Jackson.
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.activated = user.getActivated().getStatus();
		this.avatar = user.getAvatar();
		this.langKey = user.getLangKey();
		this.createUid = user.getCreateUid();
		this.createTime = user.getCreateTime();
		this.updateUid = user.getUpdateUid();
		this.updateTime = user.getUpdateTime();
		//this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
		this.authorities = user.getAuthorities();
	}
}
