package cn.codeyang.auth.service.dto;

import cn.codeyang.auth.entity.System;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yangzhongyang
 */
@Data
@ToString
public class SystemDTO implements Serializable {


	private static final long serialVersionUID = 7038656093478386747L;
	private Integer id;

	private String icon;
	/**
	 * 系统名称
	 */
	private String name;

	/**
	 * 别名
	 */
	private String code;
	/**
	 * 系统标题
	 */
	private String title;
	/**
	 * 系统描述
	 */
	private String description;

	private String basePath;

	private String status;

	public SystemDTO(){}

	public SystemDTO(System system) {
		this.id = system.getId();
		this.icon = system.getIcon();
		this.name = system.getName();
		this.code = system.getCode();
		this.title = system.getTitle();
		this.description = system.getDescription();
		this.basePath = system.getBasePath();
		if (system.getStatus() != null) {
			this.status = system.getStatus().getDesc();
		}
	}
}
