package cn.codeyang.auth.entity;

import cn.codeyang.common.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_log")
public class Log extends BaseEntity<Log> {
	private String description;
	private Integer uid;
	private Date startTime;
	private int spendTime;
	private String basePath;
	private String uri;
	private String url;
	private String method;
	private String parameter;
	private String userAgent;
	private String ip;
	private String result;
	private String permissions;
}
