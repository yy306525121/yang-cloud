package cn.codeyang.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author akafra
 */
public class UserNotActivatedException extends AuthenticationException {

	private static final long serialVersionUID = 4451723925275900045L;

	public UserNotActivatedException(String msg) {
		super(msg);
	}

	public UserNotActivatedException(String msg, Throwable t) {
		super(msg, t);
	}
}
