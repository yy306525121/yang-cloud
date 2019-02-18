package cn.codeyang.common.exception;

/**
 * @author yangzhongyang
 */
public enum ErrorEnum {
	ID_NOT_NULL("F001", "编号不可为空", false);

	private String code;
	private String message;
	private boolean canRetry;

	ErrorEnum(String code, String message, boolean canRetry) {
		this.code = code;
		this.message = message;
		this.canRetry = canRetry;
	}}
