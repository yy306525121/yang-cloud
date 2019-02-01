package cn.codeyang.auth.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author yangzhongyang
 */
public enum PermissionStatus implements IEnum {
	正常(1),禁止(2);

	@EnumValue
	private final int value;

	PermissionStatus(final int value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public Serializable getValue() {
		return value;
	}}
