package cn.codeyang.auth.api.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author akafra
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserQuery implements Serializable {

	private static final long serialVersionUID = -2820300354625453473L;

	private String username;

	private String nickname;
}
