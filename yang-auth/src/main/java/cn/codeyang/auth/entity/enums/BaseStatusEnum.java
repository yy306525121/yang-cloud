package cn.codeyang.auth.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author yangzhongyang
 */
public enum BaseStatusEnum {
	DELETED(-1, "已删除"),
	NORMAL(1, "正常");

	BaseStatusEnum(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	@EnumValue
	private final int status;

	@JsonValue
	private final String desc;

	public int getStatus
			() {
		return status;
	}

	public String getDesc() {
		return desc;
	}}

