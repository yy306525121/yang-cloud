package cn.codeyang.auth.entity.enums;

import cn.codeyang.common.exception.UnknownEnumException;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author yangzhongyang
 */
public enum PermissionType implements IEnum<Integer> {

	目录(1),
	菜单(2),
	按钮(3);

	@EnumValue
	private final int value;

	PermissionType(final int value) {
		this.value = value;
	}

	@JsonValue
	@Override
	public Integer getValue() {
		return value;
	}

	@JsonCreator
	public static PermissionType getEnum(int value) {
		for (PermissionType menuTypeEnum : PermissionType.values()) {
			if (menuTypeEnum.getValue() == value) {
				return menuTypeEnum;
			}
		}
		throw new UnknownEnumException("Error: Invalid AuthTypeEnum type value: " + value);
	}

}