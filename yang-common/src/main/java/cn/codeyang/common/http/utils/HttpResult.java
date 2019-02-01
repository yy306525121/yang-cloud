package cn.codeyang.common.http.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzhongyang
 */
@Data
public class HttpResult implements Serializable {

	private static final long serialVersionUID = 7591515022632716015L;


	private boolean status;
	private String msg;
	private Object data;

	private HttpResult() {
	}

	private HttpResult(boolean status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static HttpResult ok(Object data){
		return new HttpResult(true, null, data);
	}

	public static HttpResult fail(String msg){
		return new HttpResult(false, msg, null);
	}
}
