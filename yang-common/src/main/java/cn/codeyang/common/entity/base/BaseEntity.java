package cn.codeyang.common.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author yangzhongyang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity<T extends Model> extends Model<T> {

	@TableId(type = IdType.AUTO)
	private Integer id;

	//@TableField(fill = FieldFill.INSERT)
	//private Instant createTime;
	//@TableField(fill = FieldFill.INSERT)
	//private Integer createUid;
	//
	//
	//@TableField(fill = FieldFill.UPDATE)
	//private Instant updateTime;
	//@TableField(fill = FieldFill.UPDATE)
	//private Integer updateUid;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
