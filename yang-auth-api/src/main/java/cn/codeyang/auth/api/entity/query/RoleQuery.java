package cn.codeyang.auth.api.entity.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author akafra
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleQuery implements Serializable {

	private static final long serialVersionUID = -23879177520898123L;

	private String name;
}
