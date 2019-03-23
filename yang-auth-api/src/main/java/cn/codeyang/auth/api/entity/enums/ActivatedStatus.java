package cn.codeyang.auth.api.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author yangzhongyang
 */
public enum ActivatedStatus {
	ACTIVATE(true, "已激活"),
	INACTIVATE(false, "未激活");

	ActivatedStatus(boolean activated, String desc) {
		this.activated = activated;
		this.desc = desc;
	}

	@EnumValue
	private final boolean activated;

	@JsonValue
	private final String desc;

	public boolean getStatus() {
		return activated;
	}

	public String getDesc() {
		return desc;
	}
}
