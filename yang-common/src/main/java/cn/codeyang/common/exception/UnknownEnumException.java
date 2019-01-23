package cn.codeyang.common.exception;

/**
 * @author yangzhongyang
 */
public class UnknownEnumException extends RuntimeException {
	private String msg;

	public UnknownEnumException(String msg) {
		super(msg);
		this.msg = msg;
	}

}
