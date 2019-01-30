package cn.codeyang.common.http.utils;

/**
 * @author yangzhongyang
 */
public class HttpResult {
	private boolean status;
	private String msg;

	private HttpResult() {
	}

	private HttpResult(boolean status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static HttpResult success(String msg){
		return new HttpResult(true, msg);
	}

	public static HttpResult fail(String msg){
		return new HttpResult(false, msg);
	}



}
